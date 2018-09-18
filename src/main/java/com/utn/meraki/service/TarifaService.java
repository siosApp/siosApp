package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.TarifaModel;

public interface TarifaService {
	
	public TarifaModel crearTarifa(Integer montoTarifa);
	
	public TarifaModel editarTarifa(String idTarifa, Integer montoTarifa);
	
	public TarifaModel bajaTarifa(String idTarifa);
	
	public TarifaModel altaTarifa(String idTarifa);
	
	public List<TarifaModel> getListTarifas();
	
	public TarifaModel findTarifaById(String idTarifa);
}
