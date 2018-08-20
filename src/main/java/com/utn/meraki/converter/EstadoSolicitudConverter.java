package com.utn.meraki.converter;

import com.utn.meraki.repository.EstadoSolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.EstadoSolicitud;
import com.utn.meraki.model.EstadoSolicitudModel;

@Component("estadoSolicitudConverter")

public class EstadoSolicitudConverter {

	@Autowired
	private EstadoSolicitudRepository estadoSolicitudRepository;

	public EstadoSolicitud convertEstadoSolicitudModelToEstadoSolicitud(EstadoSolicitudModel estadoSolicitudModel) {
		EstadoSolicitud estadoSolicitud = null;
		if(estadoSolicitudModel.getId()!=null) {
			estadoSolicitud = estadoSolicitudRepository.findEstadoSolicitudById(estadoSolicitudModel.getId());
		}else {
			estadoSolicitud = new EstadoSolicitud();
		}
		estadoSolicitud.setNombreEstadoSolicitud(estadoSolicitudModel.getNombreEstadoSolicitud());
		estadoSolicitud.setFechaBaja(estadoSolicitudModel.getFechaBaja());
		estadoSolicitudRepository.save(estadoSolicitud);
		return estadoSolicitud;
	}
	
	public EstadoSolicitudModel convertEstadoSolicitudToEstadoSolicitudModel(EstadoSolicitud estadoSolicitud) {
		EstadoSolicitudModel estadoSolicitudModel = new EstadoSolicitudModel();
		estadoSolicitudModel.setId(estadoSolicitud.getId());
		estadoSolicitudModel.setNombreEstadoSolicitud(estadoSolicitud.getNombreEstadoSolicitud());
		estadoSolicitudModel.setFechaBaja(estadoSolicitud.getFechaBaja());
		return estadoSolicitudModel;
	}

}
