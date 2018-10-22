package com.utn.meraki.service;

import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.SolicitudEstado;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.model.SolicitudTerminadaModel;

import java.util.List;

public interface SolicitudService {
	
	public SolicitudEstado ultimoEstadoSolicitud(String idSolicitud);
	
	public SolicitudModel solicitarServicio(SolicitudModel solicitudModel);
	
	public SolicitudModel rechazarSolicitud(String idSolicitud);
	
	public SolicitudModel aceptarSolicitud(String idSolicitud);

	public List<SolicitudModel> getSolicitudesPendientesPorUsuario(String idUsuario);

	public List<SolicitudModel> getSolicitudesPorUsuario(String idUsuario);

	public List<SolicitudEstado> disabledAllSolicitudEstado(Solicitud solicitud);

	public SolicitudModel finalizarSolicitud(String idSolicitud, int calificacion, String comentario);
	
	public List<SolicitudTerminadaModel> listSolicitudesTerminadas();

}
