package com.utn.meraki.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Archivo;
import com.utn.meraki.entity.Calificacion;
import com.utn.meraki.entity.Comentario;
import com.utn.meraki.entity.OfertaRequerimiento;
import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.SolicitudEstado;
import com.utn.meraki.model.SolicitudCalificacionesModel;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.model.SolicitudTerminadaModel;
import com.utn.meraki.model.TrabajosOferenteModel;
import com.utn.meraki.repository.ArchivoRepository;
import com.utn.meraki.repository.CalificacionRepository;
import com.utn.meraki.repository.ComentarioRepository;
import com.utn.meraki.repository.EstadoRequerimientoRepository;
import com.utn.meraki.repository.EstadoSolicitudRepository;
import com.utn.meraki.repository.RequerimientoRepository;
import com.utn.meraki.repository.RubroRepository;
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
	CalificacionRepository calificacionRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EstadoSolicitudRepository estadoSolicitudRepository;
	@Autowired
	ComentarioRepository comentarioRepository;
	@Autowired
	ArchivoRepository archivoRepository;
	@Autowired
	EstadoRequerimientoRepository estadoRequerimientoRepository;
	@Autowired
	RequerimientoRepository requerimientoRepository;
	@Autowired
	RubroRepository rubroRepository;
		
	//CONVERTER
	@Autowired
	CalificacionConverter calificacionConverter;
	
	//SERVICE
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
		solicitud.setRubro(rubroRepository.findRubroByNombreRubro(ofertaRequerimiento.getRequerimiento().getRubro().getNombreRubro()));
		for(Archivo archivo : ofertaRequerimiento.getRequerimiento().getArchivos()) {
			solicitud.getArchivos().add(archivo);
		}
		SolicitudEstado solicitudEstado = new SolicitudEstado();
		solicitudEstado.setActivo(true);
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
		solicitudModel.setNombreRubro(solicitud.getRubro().getNombreRubro());
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
		solicitud.setRubro(rubroRepository.findRubroByNombreRubro(solicitudModel.getNombreRubro()));
		for(String urlArchivo : solicitudModel.getUrlArchivos()) {
			Archivo archivo = new Archivo(urlArchivo);
			archivoRepository.save(archivo);
			solicitud.getArchivos().add(archivo);
		}
		return solicitud;
	}
	
	public SolicitudTerminadaModel convertSolicitudToSolicitudTerminadaModel(Solicitud solicitud) {
		SolicitudTerminadaModel solicitudTerminadaModel = new SolicitudTerminadaModel();
		solicitudTerminadaModel.setIdSolicitud(solicitud.getId());
		solicitudTerminadaModel.setDescripcion(solicitud.getDescripcion());
		solicitudTerminadaModel.setFechaSolicitud(solicitud.getFechaSolicitud());
		solicitudTerminadaModel.setUsernameDemandante(solicitud.getUsuarioDemandante().getUsername());
		solicitudTerminadaModel.setUsernameOferente(solicitud.getUsuarioOferente().getUsername());
		for(Calificacion calificacion : calificacionRepository.findCalificacionBySolicitud(solicitud)) {
			solicitudTerminadaModel.getCalificaciones().add(calificacionConverter.convertCalificacionToCalificacionModel(calificacion));
		}
		for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
			if(solicitudEstado.isActivo()) {
				solicitudTerminadaModel.setFechaFinalizacion(solicitudEstado.getFechaCambioEstado());
			}
		}
		return solicitudTerminadaModel;
	}
	
	public SolicitudCalificacionesModel convertSolicitudToSolicitudCalificacionesModel(Solicitud solicitud) {
		SolicitudCalificacionesModel solicitudCalificacionesModel = new SolicitudCalificacionesModel();
		solicitudCalificacionesModel.setDescripcion(solicitud.getDescripcion());
		solicitudCalificacionesModel.setFechaSolicitud(solicitud.getFechaSolicitud());
		solicitudCalificacionesModel.setMail(solicitud.getUsuarioDemandante().getMail());
		solicitudCalificacionesModel.setNombreUsuario(solicitud.getUsuarioDemandante().getNombre());
		solicitudCalificacionesModel.setNombreApellido(solicitud.getUsuarioDemandante().getApellido());
		for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
			if(solicitudEstado.isActivo()) {
				solicitudCalificacionesModel.setFechaFinalizacion(solicitudEstado.getFechaCambioEstado());
			}
		}
		Calificacion calificacion = calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud, solicitud.getUsuarioOferente());
		solicitudCalificacionesModel.setCalificacion(calificacion.getCalificacion());
		for(Comentario comentario : calificacion.getComentarios()) {
			solicitudCalificacionesModel.getComentarios().add(comentario.getDescripcion());
		}
		return solicitudCalificacionesModel;
	}
	
	public TrabajosOferenteModel convertSolicitudToTrabajoOferenteModel(Solicitud solicitud) {
		TrabajosOferenteModel trabajosOferenteModel = new TrabajosOferenteModel();
		trabajosOferenteModel.setDescripcion(solicitud.getDescripcion());
		trabajosOferenteModel.setMailDemandante(solicitud.getUsuarioDemandante().getMail());
		trabajosOferenteModel.setNombreRubro(solicitud.getRubro().getNombreRubro());
		Calificacion calificacion = calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud, solicitud.getUsuarioDemandante());
		if(calificacion!=null && calificacion.getComentarios()!=null){
			for(Comentario comentario : calificacionRepository.findCalificacionBySolicitudAndUsuario
					(solicitud, solicitud.getUsuarioOferente()).getComentarios()) {
				trabajosOferenteModel.getComentarios().add(comentario.getDescripcion());
			}
		}
		return trabajosOferenteModel;
	}

}
