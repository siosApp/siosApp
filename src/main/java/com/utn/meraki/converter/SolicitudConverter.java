package com.utn.meraki.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Archivo;
import com.utn.meraki.entity.OfertaRequerimiento;
import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.SolicitudEstado;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.repository.ArchivoRepository;
import com.utn.meraki.repository.EstadoRequerimientoRepository;
import com.utn.meraki.repository.EstadoSolicitudRepository;
import com.utn.meraki.repository.RequerimientoRepository;
import com.utn.meraki.repository.SolicitudEstadoRepository;
import com.utn.meraki.repository.SolicitudRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.service.SolicitudService;

@Component("solicitudConverter")

public class SolicitudConverter {
	
	//REPOSITORY
	@Autowired
	SolicitudRepository solicitudRepository;
	@Autowired
	SolicitudEstadoRepository solicitudEstadoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EstadoSolicitudRepository estadoSolicitudRepository;
	@Autowired
	ArchivoRepository archivoRepository;
	@Autowired
	EstadoRequerimientoRepository estadoRequerimientoRepository;
	@Autowired
	RequerimientoRepository requerimientoRepository;
	@Autowired
	SolicitudService solicitudService;
	
	public Solicitud convertOfertaRequerimientoToSolicitud(OfertaRequerimiento ofertaRequerimiento) {
		Requerimiento requerimiento = ofertaRequerimiento.getRequerimiento();
		requerimiento.setEstadoRequerimiento(estadoRequerimientoRepository.findEstadoRequerimientoByNombreEstado("Activo"));
		requerimientoRepository.save(requerimiento);
		Solicitud solicitud = new Solicitud();
		solicitud.setDescripcion(requerimiento.getDescripcion());
		solicitud.setFechaSolicitud(new Date(System.currentTimeMillis()));
		solicitud.setUsuarioOferente(requerimiento.getUsuario());
		solicitud.setUsuarioDemandante(ofertaRequerimiento.getUsuario());
		//solicitud.setArchivos(archivos);
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setEstadoSolicitud(estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Aceptada"));
		solicitudEstado.setFechaCambioEstado(new Date(System.currentTimeMillis()));
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		return solicitud;
	}
	
	public SolicitudModel convertSolicitudToSolicitudModel(Solicitud solicitud) {
		SolicitudModel solicitudModel = new SolicitudModel();
		solicitudModel.setId(solicitud.getId());
		solicitudModel.setDescripcion(solicitud.getDescripcion());
		solicitudModel.setFechaSolicitud(solicitud.getFechaSolicitud());
		solicitudModel.setUsuarioDemandante(solicitud.getUsuarioDemandante().getUsername());
		solicitudModel.setUsuarioOferente(solicitud.getUsuarioOferente().getUsername());
		solicitudModel.setNombreEstadoSolicitud(solicitudService.ultimoEstadoSolicitud(solicitud.getId())
				.getEstadoSolicitud().getNombreEstadoSolicitud());
		for(Archivo archivo : solicitud.getArchivos()) {
			solicitudModel.getUrlArchivos().add(archivo.getUrlArchivo());
		}
		return solicitudModel;
	}
	
	public Solicitud convertSolicitudModelToSolicitud(SolicitudModel solicitudModel) {
		Solicitud solicitud = new Solicitud();
		solicitud.setDescripcion(solicitudModel.getDescripcion());
		solicitud.setFechaSolicitud(new Date(System.currentTimeMillis()));
		solicitud.setUsuarioDemandante(usuarioRepository.findUsuarioByUsername(solicitudModel.getUsuarioDemandante()));
		solicitud.setUsuarioOferente(usuarioRepository.findUsuarioByUsername(solicitudModel.getUsuarioOferente()));
		for(String urlArchivo : solicitudModel.getUrlArchivos()) {
			Archivo archivo = new Archivo(urlArchivo);
			archivoRepository.save(archivo);
			solicitud.getArchivos().add(archivo);
		}
		return solicitud;
	}

}
