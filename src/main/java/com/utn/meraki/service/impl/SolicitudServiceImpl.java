package com.utn.meraki.service.impl;

import java.sql.Date;

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
	
	//CONVERTER
	@Autowired
	SolicitudConverter solicitudConverter;
	
	@Override
	public SolicitudEstado ultimoEstadoSolicitud(String idSolicitud) {
		Date fechaEstado = null;
		for(SolicitudEstado solicitudEstado : solicitudRepository.findSolicitudById(idSolicitud).getSolicitudEstados()) {
			if(fechaEstado==null) {
				fechaEstado = solicitudEstado.getFechaCambioEstado();
			}else {
				if(fechaEstado.before(solicitudEstado.getFechaCambioEstado())) {
					fechaEstado = solicitudEstado.getFechaCambioEstado();
				}
			}
		}
		return solicitudEstadoRepository.findSolicitudEstadoByFechaCambioEstado(fechaEstado);
	}

	@Override
	public SolicitudModel solicitarServicio(SolicitudModel solicitudModel) {
		Solicitud solicitud = solicitudConverter.convertSolicitudModelToSolicitud(solicitudModel);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setFechaCambioEstado(new Date(System.currentTimeMillis()));
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Creada"));
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

}
