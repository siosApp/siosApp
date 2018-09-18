package com.utn.meraki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utn.meraki.model.TarifaModel;
import com.utn.meraki.service.TarifaService;

@RequestMapping("/tarifa")
@RestController
@CrossOrigin

public class TarifaController {
	
	//SERVICE
	@Autowired
	TarifaService tarifaService;
	
	@PostMapping("/crearTarifa")
	public TarifaModel crearTarifa(@RequestParam(value="montoTarifa",required=true)Integer montoTarifa) {
		return tarifaService.crearTarifa(montoTarifa);
	}
	
	@PutMapping("/editarTarifa")
	public TarifaModel editarTarifa(@RequestParam(value="idTarifa",required=true)String idTarifa,
			@RequestParam(value="montoTarifa",required=true)Integer montoTarifa) {
		return tarifaService.editarTarifa(idTarifa, montoTarifa);
	}
	
	@PutMapping("/bajaTarifa")
	public TarifaModel bajaTarifa(@RequestParam(value="idTarifa",required=true)String idTarifa) {
		return tarifaService.bajaTarifa(idTarifa);
	}
	
	@PutMapping("/habilitarTarifa")
	public TarifaModel habilitarTarifa(@RequestParam(value="idTarifa",required=true)String idTarifa) {
		return tarifaService.altaTarifa(idTarifa);
	}
	
	@GetMapping("/getListTarifa")
	public List<TarifaModel> getListTarifa(){
		return tarifaService.getListTarifas();
	}
	
	@GetMapping("/findTarifaById")
	public TarifaModel findTarifaById(@RequestParam(value="idTarifa",required=true)String idTarifa) {
		return tarifaService.findTarifaById(idTarifa);
	}
}
