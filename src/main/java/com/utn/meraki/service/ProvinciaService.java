package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.ProvinciaModel;

public interface ProvinciaService {
	
	public abstract ProvinciaModel crearProvincia(ProvinciaModel provinciaModel);
	
	public abstract ProvinciaModel editarProvincia(ProvinciaModel provinciaModel);
	
	public abstract List<ProvinciaModel> listProvinciaVigente();
	
	public abstract ProvinciaModel habilitarProvincia(String id);

}
