package com.utn.meraki.controller;

import com.utn.meraki.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.model.SolicitudTerminadaModel;
import com.utn.meraki.service.SolicitudService;

import java.util.List;

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
	
	@PutMapping("/aceptarSolicitud")
	public SolicitudModel aceptarSolicitud(@RequestParam(value="idSolicitud",required=true)String idSolicitud) {
		return solicitudService.aceptarSolicitud(idSolicitud);
	}
	@GetMapping("/solicitudesPendientesPorUsuario")
	public List<SolicitudModel> getSolicitudesPendientesPorUsuario(@RequestParam(value="idUsuario") String idUsuario){
		return solicitudService.getSolicitudesPendientesPorUsuario(idUsuario);
	}
	@GetMapping("/cantidadSolicitudesPendientesPorUsuario")
	public int getCantidadSolicitudesPendientesPorUsuario(@RequestParam(value="idUsuario") String idUsuario){
		return solicitudService.getSolicitudesPendientesPorUsuario(idUsuario).size();
	}
	@GetMapping("/solicitudesPorUsuario")
	public List<SolicitudModel> getSolicitudesPorUsuario(@RequestParam(value="idUsuario") String idUsuario){
		return solicitudService.getSolicitudesPorUsuario(idUsuario);
	}

	@PutMapping("/finalizarSolicitud")
	public SolicitudModel aceptarSolicitud(@RequestParam(value="idSolicitud",required=true)String idSolicitud,
										   @RequestParam(value="calificacion",required=true)int calificacion,
										   @RequestParam(value="comentario",required=true)String comentario) {
		return solicitudService.finalizarSolicitud(idSolicitud,calificacion,comentario);
	}
	
	@GetMapping("/listSolicitudesFinalizadas")
	public List<SolicitudTerminadaModel> listSolicitudesFinalizadas(){
		return solicitudService.listSolicitudesTerminadas();
	}
}
