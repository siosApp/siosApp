package com.utn.meraki.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.entity.Tarifa;
import com.utn.meraki.repository.TarifaRepository;
import com.utn.meraki.service.TarifaService;

@Service("tarifaService")

public class TarifaServiceImpl implements TarifaService{

	//REPOSITORY
	@Autowired
	TarifaRepository tarifaRepository;
	
	@Override
	public Integer crearTarifa(Integer montoTarifa) {
		Tarifa tarifa = new Tarifa();
		tarifa.setMonto(montoTarifa);
		tarifaRepository.save(tarifa);
		return montoTarifa;
	}

	@Override
	public Integer editarTarifa(String idTarifa, Integer montoTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		tarifa.setMonto(montoTarifa);
		tarifaRepository.save(tarifa);
		return montoTarifa;
	}

	@Override
	public String bajaTarifa(String idTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		tarifa.setFechaBaja(new Date(System.currentTimeMillis()));
		tarifaRepository.save(tarifa);
		return "La tarifa se dio de baja";
	}

	@Override
	public String altaTarifa(String idTarifa) {
		Tarifa tarifa = tarifaRepository.findTarifaById(idTarifa);
		tarifa.setFechaBaja(null);
		tarifaRepository.save(tarifa);
		return "La tarifa fue habilitada";
	}

	@Override
	public List<Integer> getListTarifas() {
		List<Integer> listTarifas = new ArrayList<>();
		for(Tarifa tarifa : tarifaRepository.findAll()) {
			listTarifas.add(tarifa.getMonto());
		}
		return listTarifas;
	}

}
