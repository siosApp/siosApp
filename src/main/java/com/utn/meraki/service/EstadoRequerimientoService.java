package com.utn.meraki.service;

import com.utn.meraki.model.EstadoRequerimientoModel;

import java.util.List;

public interface EstadoRequerimientoService {

	public abstract EstadoRequerimientoModel crearEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel);
	
	public abstract EstadoRequerimientoModel editarEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel);
	
	public abstract List<EstadoRequerimientoModel> listaEstadoRequerimientoVigente();
	
	public abstract EstadoRequerimientoModel habilitarEstadoRequerimiento(String id);
}
