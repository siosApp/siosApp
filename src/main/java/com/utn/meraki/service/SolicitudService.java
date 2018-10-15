package com.utn.meraki.service;

import com.utn.meraki.entity.SolicitudEstado;
import com.utn.meraki.model.SolicitudModel;

import java.util.List;

public interface SolicitudService {
	
	public SolicitudEstado ultimoEstadoSolicitud(String idSolicitud);
	
	public SolicitudModel solicitarServicio(SolicitudModel solicitudModel);
	
	public SolicitudModel rechazarSolicitud(String idSolicitud);
	
	public SolicitudModel aceptarSolicitud(String idSolicitud);

	public List<SolicitudModel> getSolicitudesPendientesPorUsuario(String idUsuario);
	
}
