package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.LocalidadModel;

public interface LocalidadService {
	
	public abstract LocalidadModel crearLocalidad(LocalidadModel localidadModel);
	
	public abstract LocalidadModel editarLocalidad(LocalidadModel localidadModel);
	
	public abstract LocalidadModel habilitarLocalidad(String id);
	
	public abstract List<LocalidadModel> listLocalidadVigente();

	public abstract List<LocalidadModel> listLocalidadesVigenteByDepartamento(String departamento);

	public abstract List<LocalidadModel> listLocalidadTodos();

	public abstract LocalidadModel getLocalidadById(String id);

	public abstract LocalidadModel getLocalidadByDomicilio(String id);

	public abstract LocalidadModel deshabilitarLocalidad(String id);

	public abstract List<LocalidadModel> findLocalidadesByProvinciaAndDepartamento(String nombreProvincia,String nombreDepartamento);

	public abstract LocalidadModel findLocalidadesByNombreLocalidadProvinciaAndDepartamento(String nombreLocalidad,String nombreProvincia,String nombreDepartamento);


}