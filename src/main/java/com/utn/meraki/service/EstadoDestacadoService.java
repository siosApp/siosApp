package com.utn.meraki.service;


import java.util.List;

import com.utn.meraki.model.EstadoDestacadoModel;
//Estado de destacado
public interface EstadoDestacadoService {
	
	public abstract EstadoDestacadoModel crearEstadoDestacado(EstadoDestacadoModel estadoDestacadoModel);
	
	public abstract EstadoDestacadoModel editarEstadoDestacado(EstadoDestacadoModel estadoDestacadoModel);
	
	public abstract List<EstadoDestacadoModel> listEstadoDestacadoVigente();
	
	public abstract EstadoDestacadoModel habilitarEstadoDestacado(String id);
	
	public abstract EstadoDestacadoModel deshabilitarEstadoDestacado(String id);
	
	public abstract EstadoDestacadoModel getEstadoDestacadoById(String id);
	
	public abstract List<EstadoDestacadoModel> listEstadoDestacadoTodas();

}
