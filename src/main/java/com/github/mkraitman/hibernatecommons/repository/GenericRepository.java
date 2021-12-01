package com.github.mkraitman.hibernatecommons.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

public interface GenericRepository<E, I> {

	public E crearEntidad(E entidad);

	public void eliminarEntidadById(I id);

	public E consultarEntidadById(I id);

	public E modificarEntidad(E entidad);

	public void crearEntidad(List<E> entidades);

	public List<E> getDataByQuery(final CriteriaQuery<E> criteria);

	public List<E> getAllData();

	public List<Object[]> getDataFieldByQuery(final CriteriaQuery<Object[]> criteria);

	public E getDataByIdCriteria(CriteriaQuery<E> criteria);

	/**
	 * Devuelve el nombre del atributo relacionado con la primary key de la entity.
	 */
	public String getIdentity();
}
