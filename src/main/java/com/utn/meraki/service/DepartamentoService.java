package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.DepartamentoModel;

public interface DepartamentoService {
	
	public abstract DepartamentoModel crearDepartamento(DepartamentoModel departamentoModel);
	
	public abstract DepartamentoModel editarDepartamento(DepartamentoModel departamentoModel);
	
	public abstract DepartamentoModel habilitarDepartamento(String id);
	
	public abstract List<DepartamentoModel> listDepartamentoVigente();
}
