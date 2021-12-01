package com.github.mkraitman.hibernatecommons.repository.controllers.implementations;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.github.mkraitman.hibernatecommons.repository.GenericRepository;

@Service
@EnableAutoConfiguration
public abstract class GenericController<E, I> implements GenericRepository<E, I> {

	private EntityManager entityManager;

	@Autowired
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	private Class<E> type;

	@SuppressWarnings("unchecked")
	public GenericController() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<E>) pt.getActualTypeArguments()[0];
	}

	@Override
	public E crearEntidad(E entidad) {
		entityManager.persist(entidad);
		entityManager.flush();
		this.entityManager.close();
		return entidad;
	}

	@Override
	public void crearEntidad(List<E> entidades) {
		for (E entidad : entidades) {
			entityManager.persist(entidad);
		}
		this.entityManager.close();
	}

	@Override
	public void eliminarEntidadById(final I id) {
		this.entityManager.remove(this.consultarEntidadById(id));
		this.entityManager.close();
	}

	@Override
	public E consultarEntidadById(final I id) {
		return this.findById(id, this.getIdentity());
	}

	@Override
	public E modificarEntidad(final E entity) {
		entityManager.merge(entity);
		entityManager.flush();
		entityManager.close();
		return entity;
	}

	@Override
	public E getDataByIdCriteria(final CriteriaQuery<E> criteria) {
		E entity;
		try {
			entity = this.getEntityManager().createQuery(criteria).getSingleResult();
		} catch (HibernateException e) {
			throw new RuntimeException("ERR", e);
		} finally {
			this.getEntityManager().close();
		}
		return entity;
	}

	@Override
	public List<E> getAllData() {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(type);
		Root<E> rootEntry = cq.from(type);
		CriteriaQuery<E> all = cq.select(rootEntry);
		return getData(all);
	}

	@Override
	public List<E> getDataByQuery(final CriteriaQuery<E> criteria) {
		return getData(criteria);
	}

	private List<E> getData(final CriteriaQuery<E> criteria) {

		List<E> entityList = new ArrayList<>();
		try {
			entityList = this.getEntityManager().createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			throw new RuntimeException("ERR", e);
		} finally {
			this.getEntityManager().close();
		}
		return entityList;
	}

	@Override
	public List<Object[]> getDataFieldByQuery(final CriteriaQuery<Object[]> criteria) {
		List<Object[]> dataField = new ArrayList<>();
		try {
			dataField = this.getEntityManager().createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			throw new RuntimeException("ERR", e);
		} finally {
			this.getEntityManager().close();
		}
		return dataField;
	}

	private E findById(I id, String atribute) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(type);
		Root<E> model = cq.from(type);
		cq.where(cb.equal(model.get(atribute), id));
		TypedQuery<E> q = entityManager.createQuery(cq);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
