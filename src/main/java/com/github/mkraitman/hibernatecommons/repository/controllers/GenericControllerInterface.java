package com.github.mkraitman.hibernatecommons.repository.controllers;

public interface GenericControllerInterface<E, I> {

	public E crearEntidad(E entidad);

	public void eliminarEntidadById(I id);

	public E consultarEntidadById(I id);

	public E modificarEntidad(E entidad);
}
