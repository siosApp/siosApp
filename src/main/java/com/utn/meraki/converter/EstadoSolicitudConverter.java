package com.utn.meraki.converter;

import org.springframework.stereotype.Component;
import com.utn.meraki.entity.EstadoSolicitud;
import com.utn.meraki.model.EstadoSolicitudModel;

@Component("estadoSolicitudConverter")

public class EstadoSolicitudConverter {
	
	public EstadoSolicitud convertEstadoSolicitudModelToEstadoSolicitud(EstadoSolicitudModel estadoSolicitudModel) {
		EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
		estadoSolicitud.setId(estadoSolicitudModel.getId());
		estadoSolicitud.setNombreEstadoSolicitud(estadoSolicitudModel.getNombreEstadoSolicitud());
		estadoSolicitud.setFechaBaja(estadoSolicitudModel.getFechaBaja());
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
