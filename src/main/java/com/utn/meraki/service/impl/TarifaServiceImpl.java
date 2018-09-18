package com.utn.meraki.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.TarifaConverter;
import com.utn.meraki.entity.Tarifa;
import com.utn.meraki.model.TarifaModel;
import com.utn.meraki.repository.TarifaRepository;
import com.utn.meraki.service.TarifaService;

@Service("tarifaService")

public class TarifaServiceImpl implements TarifaService{

	//REPOSITORY
	@Autowired
	TarifaRepository tarifaRepository;
	
	@Autowired
	TarifaConverter tarifaConverter;
	
	@Override
	public TarifaModel crearTarifa(Integer montoTarifa) {
		Tarifa tarifa = new Tarifa();
		tarifa.setMonto(montoTarifa);
		tarifaRepository.save(tarifa);
		return tarifaConverter.convertTarifaToTarifaModel(tarifa);
	}

	@Override
	public TarifaModel editarTarifa(String idTarifa, Integer montoTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		tarifa.setMonto(montoTarifa);
		tarifaRepository.save(tarifa);
		return tarifaConverter.convertTarifaToTarifaModel(tarifa);
	}

	@Override
	public TarifaModel bajaTarifa(String idTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		tarifa.setFechaBaja(new Date(System.currentTimeMillis()));
		tarifaRepository.save(tarifa);
		return tarifaConverter.convertTarifaToTarifaModel(tarifa);
	}

	@Override
	public TarifaModel altaTarifa(String idTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		tarifa.setFechaBaja(null);
		tarifaRepository.save(tarifa);
		return tarifaConverter.convertTarifaToTarifaModel(tarifa);
	}

	@Override
	public List<TarifaModel> getListTarifas() {
		List<TarifaModel> listTarifas = new ArrayList<>();
		for(Tarifa tarifa : tarifaRepository.findAll()) {
			listTarifas.add(tarifaConverter.convertTarifaToTarifaModel(tarifa));
		}
		return listTarifas;
	}

	@Override
	public TarifaModel findTarifaById(String idTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		return tarifaConverter.convertTarifaToTarifaModel(tarifa);
	}

}
