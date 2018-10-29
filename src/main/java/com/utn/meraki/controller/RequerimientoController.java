package com.utn.meraki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utn.meraki.model.OfertaRequerimientoModel;
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.service.RequerimientoService;

@RequestMapping("/requerimiento")
@RestController
@CrossOrigin

public class RequerimientoController {
	
	//SERVICE
	@Autowired
	RequerimientoService requerimientoService;
	
	//CONTROLADORES
	@PostMapping("/publicarRequerimiento")
	public RequerimientoModel publicarRequerimiento(@RequestBody RequerimientoModel requerimientoModel) {
		return requerimientoService.publicarRequerimiento(requerimientoModel);
	}
	
	@PostMapping("/ofertarRequerimiento")
	public OfertaRequerimientoModel ofertarRequerimiento(@RequestBody OfertaRequerimientoModel ofertaRequerimientoModel) {
		return requerimientoService.ofertarRequerimiento(ofertaRequerimientoModel);
	}
	
	@GetMapping("/getRequerimientosActivos")
	public List<RequerimientoModel> getRequerimientosActivos(){
		return requerimientoService.getRequerimientosActivos();
	}
	
	@GetMapping("/findRequerimientoById")
	public RequerimientoModel findRequerimientoById(@RequestParam(value="idRequerimiento",required=true)String idRequerimiento) {
		return requerimientoService.findRequerimientoById(idRequerimiento);
	}
	
	@PostMapping("/aceptarOfertaRequerimiento")
	public SolicitudModel aceptarOfertaRequerimiento(@RequestParam(value="idOfertaRequerimiento",required=true)
			String idOfertaRequerimiento) {
		return requerimientoService.aceptarOfertaRequerimiento(idOfertaRequerimiento);
	}
	
	@GetMapping("/misRequerimientos")
	public List<RequerimientoModel> misRequerimientos(@RequestParam(value="idUsuario",required=true)String idUsuario) {
		return requerimientoService.misRequerimientos(idUsuario);
	}
	
	

}
