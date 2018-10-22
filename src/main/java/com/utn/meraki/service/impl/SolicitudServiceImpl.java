package com.utn.meraki.service.impl;


import java.text.SimpleDateFormat;
import java.util.*;

import com.utn.meraki.entity.*;
import com.utn.meraki.repository.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.SolicitudConverter;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.model.SolicitudTerminadaModel;
import com.utn.meraki.service.SolicitudService;

@Service("solicitudServiceImpl")

public class SolicitudServiceImpl implements SolicitudService{

	//REPOSITORY
	@Autowired
	SolicitudRepository solicitudRepository;
	@Autowired
	SolicitudEstadoRepository solicitudEstadoRepository;
	@Autowired
	EstadoSolicitudRepository estadoSolicitudRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	//CONVERTER
	@Autowired
	SolicitudConverter solicitudConverter;
	@Autowired
	ComentarioRepository comentarioRepository;
	@Autowired
	CalificacionRepository calificacionRepository;

	@Override
	public SolicitudEstado ultimoEstadoSolicitud(String idSolicitud) {
		for (SolicitudEstado solicitudEstado:solicitudRepository.findSolicitudById(idSolicitud).getSolicitudEstados())
			if(solicitudEstado.isActivo()){
				return solicitudEstado;
			}
		return new SolicitudEstado();
	}

	@Override
	public SolicitudModel solicitarServicio(SolicitudModel solicitudModel) {
		Solicitud solicitud = solicitudConverter.convertSolicitudModelToSolicitud(solicitudModel);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date());
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Creada"));
		solicitudEstado.setActivo(true);
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public SolicitudModel rechazarSolicitud(String idSolicitud) {
		Solicitud solicitud = solicitudRepository.findSolicitudById(idSolicitud);
		this.disabledAllSolicitudEstado(solicitud);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date(System.currentTimeMillis()));
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Rechazada"));
		solicitudEstado.setActivo(true);
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public SolicitudModel aceptarSolicitud(String idSolicitud) {
		Solicitud solicitud = solicitudRepository.findSolicitudById(idSolicitud);
		this.disabledAllSolicitudEstado(solicitud);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date());
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Aceptada"));
		solicitudEstado.setActivo(true);
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public List<SolicitudModel> getSolicitudesPendientesPorUsuario(String idUsuario) {
		List<SolicitudModel> solicitudModels=new ArrayList<>();
		Usuario usuario=usuarioRepository.findUsuarioById(idUsuario);
		EstadoSolicitud estadoSolicitud=estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Creada");
		List<Solicitud> solicitudList=solicitudRepository.findSolicitudByUsuarioOferente(usuario);
		for(Solicitud solicitud: solicitudList){
			if(this.ultimoEstadoSolicitud(solicitud.getId()).getEstadoSolicitud().equals(estadoSolicitud)){
				solicitudModels.add(solicitudConverter.convertSolicitudToSolicitudModel(solicitud));
			}
		}
		return solicitudModels;
	}

	@Override
	public List<SolicitudModel> getSolicitudesPorUsuario(String idUsuario) {
		List<SolicitudModel> solicitudModels=new ArrayList<>();
		Usuario usuario=usuarioRepository.findUsuarioById(idUsuario);
		List<Solicitud> solicitudList=solicitudRepository.findSolicitudByUsuarioOferente(usuario);
		for(Solicitud solicitud: solicitudList){
			solicitudModels.add(solicitudConverter.convertSolicitudToSolicitudModel(solicitud));
		}
		return solicitudModels;
	}

	@Override
	public List<SolicitudEstado> disabledAllSolicitudEstado(Solicitud solicitud) {
		List<SolicitudEstado> solicitudEstados =solicitud.getSolicitudEstados();
		for(SolicitudEstado solicitudEstado:solicitudEstados){
			solicitudEstado.setActivo(false);
			solicitudEstadoRepository.save(solicitudEstado);
		}
		return solicitudEstados;
	}

	@Override
	public SolicitudModel finalizarSolicitud(String idSolicitud, int calificacion, String comentario) {
		Solicitud solicitud= solicitudRepository.findSolicitudById(idSolicitud);
		disabledAllSolicitudEstado(solicitud);
		EstadoSolicitud estadoSolicitud=estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Finalizada");
		SolicitudEstado solicitudEstado=new SolicitudEstado();
		solicitudEstado.setActivo(true);
		solicitudEstado.setEstadoSolicitud(estadoSolicitud);
		solicitudEstado.setFechaCambioEstado(new Date());
		//Creacion comentario.
		Comentario comment= new Comentario();
		comment.setDescripcion(comentario);
		comentarioRepository.save(comment);
		//Creacion calificacion
		Calificacion califcation= new Calificacion();
		califcation.setCalificacion(calificacion);
		califcation.setComentarios(Arrays.asList(comment));
		califcation.setSolicitud(solicitud);
		califcation.setFechaCalificacion(new java.sql.Date(System.currentTimeMillis()));
		califcation.setUsuario(solicitud.getUsuarioDemandante());
		calificacionRepository.save(califcation);
		//Guardando solicitud
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public List<SolicitudTerminadaModel> listSolicitudesTerminadas() {
		List<SolicitudTerminadaModel> solicitudesTerminadas = new ArrayList<>();
		for(Solicitud solicitud : solicitudRepository.findAll()) {
			for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
				if(solicitudEstado.isActivo()) {
					System.out.println("último estado de la solicitud");
					if(solicitudEstado.getEstadoSolicitud().getNombreEstadoSolicitud().equals("Finalizada")) {
						solicitudesTerminadas.add(solicitudConverter.convertSolicitudToSolicitudTerminadaModel(solicitud));
					}
				}
			}
		}
		return solicitudesTerminadas;
	}

}
