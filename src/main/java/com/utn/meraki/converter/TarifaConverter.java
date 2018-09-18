package com.utn.meraki.converter;

import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Tarifa;
import com.utn.meraki.model.TarifaModel;


@Component("tarifaConverter")

public class TarifaConverter {
	
	public TarifaModel convertTarifaToTarifaModel(Tarifa tarifa) {
		TarifaModel tarifaModel = new TarifaModel();
		tarifaModel.setId(tarifa.getId());
		tarifaModel.setFechaBaja(tarifa.getFechaBaja());
		tarifaModel.setMontoTarifa(tarifa.getMonto());
		return tarifaModel;
	}

}
