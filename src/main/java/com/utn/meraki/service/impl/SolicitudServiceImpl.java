package com.utn.meraki.service.impl;


import java.text.SimpleDateFormat;
import java.util.*;

import com.utn.meraki.entity.EstadoSolicitud;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.repository.UsuarioRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.SolicitudConverter;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.SolicitudEstado;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.repository.EstadoSolicitudRepository;
import com.utn.meraki.repository.SolicitudEstadoRepository;
import com.utn.meraki.repository.SolicitudRepository;
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
	
	@Override
	public SolicitudEstado ultimoEstadoSolicitud(String idSolicitud) {
		List<SolicitudEstado> solicitudEstados= solicitudRepository.findSolicitudById(idSolicitud).getSolicitudEstados();
		Collections.sort(solicitudEstados);
		return solicitudEstados.get(0);
	}

	@Override
	public SolicitudModel solicitarServicio(SolicitudModel solicitudModel) {
		Solicitud solicitud = solicitudConverter.convertSolicitudModelToSolicitud(solicitudModel);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date());
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Creada"));
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public SolicitudModel rechazarSolicitud(String idSolicitud) {
		Solicitud solicitud = solicitudRepository.findSolicitudById(idSolicitud);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date(System.currentTimeMillis()));
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Rechazada"));
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public SolicitudModel aceptarSolicitud(String idSolicitud) {
		Solicitud solicitud = solicitudRepository.findSolicitudById(idSolicitud);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date(System.currentTimeMillis()));
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Aceptada"));
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

}
