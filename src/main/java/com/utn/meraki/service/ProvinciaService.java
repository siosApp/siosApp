package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.ProvinciaModel;
//Provincia
public interface ProvinciaService {
	
	public abstract ProvinciaModel crearProvincia(ProvinciaModel provinciaModel);
	
	public abstract ProvinciaModel editarProvincia(ProvinciaModel provinciaModel);
	
	public abstract List<ProvinciaModel> listProvinciaVigente();
	
	public abstract ProvinciaModel habilitarProvincia(String id);
	
	public abstract ProvinciaModel deshabilitarProvincia(String id);
	
	public abstract ProvinciaModel getProvinciaById(String id);
	
	public abstract List<ProvinciaModel> listProvinciaTodas();

}
