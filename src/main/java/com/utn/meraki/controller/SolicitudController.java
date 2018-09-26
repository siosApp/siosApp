package com.utn.meraki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.service.SolicitudService;

@RequestMapping("/solicitud")
@RestController
@CrossOrigin

public class SolicitudController {
	
	//SERVICE
	@Autowired
	SolicitudService solicitudService;
	
	//CONTROLADORES
	@PostMapping("/solicitarServicio")
	public SolicitudModel solicitarServicio(@RequestBody SolicitudModel solicitudModel) {
		return solicitudService.solicitarServicio(solicitudModel);
	}
	
	@PutMapping("/rechazarSolicitud")
	public SolicitudModel rechazarSolicitud(@RequestParam(value="idSolicitud",required=true)String idSolicitud) {
		return solicitudService.rechazarSolicitud(idSolicitud);
	}

}
