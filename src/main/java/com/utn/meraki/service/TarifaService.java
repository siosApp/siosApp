package com.utn.meraki.service;

import java.util.List;

public interface TarifaService {
	
	public Integer crearTarifa(Integer montoTarifa);
	
	public Integer editarTarifa(String idTarifa, Integer montoTarifa);
	
	public String bajaTarifa(String idTarifa);
	
	public String altaTarifa(String idTarifa);
	
	public List<Integer> getListTarifas();
}
