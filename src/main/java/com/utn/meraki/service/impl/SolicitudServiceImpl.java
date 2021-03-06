package com.utn.meraki.service.impl;


import java.text.SimpleDateFormat;
import java.util.*;

import com.utn.meraki.entity.*;
import com.utn.meraki.model.SolicitudDemandanteModel;
import com.utn.meraki.repository.*;
import com.utn.meraki.service.MailService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.SolicitudConverter;
import com.utn.meraki.model.SolicitudCalificacionesModel;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.model.SolicitudTerminadaModel;
import com.utn.meraki.model.TrabajosOferenteModel;
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
	@Autowired
	MailService mailService;

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
		if(solicitud.getUsuarioDemandante().getMail() !=null){
			mailService.enviarMail(solicitud.getUsuarioDemandante().getMail(),"Notificación solicitud rechazada - SIOS","La solicitud ha sido rechazada. Verifique su estado en SIOS.");
		}
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
		if(solicitud.getUsuarioDemandante().getMail() !=null){
			mailService.enviarMail(solicitud.getUsuarioDemandante().getMail(),"Notificación solicitud aceptada - SIOS","La solicitud ha sido aceptada. Verifique su estado en SIOS.");
		}
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override
	public List<SolicitudModel> getSolicitudesPendientesPorUsuario(String idUsuario) {
		List<SolicitudModel> solicitudModels=new ArrayList<>();
		Usuario usuario=usuarioRepository.findUsuarioById(idUsuario);
		EstadoSolicitud estadoSolicitud=estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Creada");
		List<Solicitud> solicitudList=solicitudRepository.findSolicitudByUsuarioOferente(usuario);
		if(solicitudList!=null) {
			for(Solicitud solicitud: solicitudList){
				System.out.println(estadoSolicitud.getNombreEstadoSolicitud());
				if(this.ultimoEstadoSolicitud(solicitud.getId()).getEstadoSolicitud().equals(estadoSolicitud)){
					solicitudModels.add(solicitudConverter.convertSolicitudToSolicitudModel(solicitud));
				}
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
		califcation.setUsuario(solicitud.getUsuarioOferente());
		calificacionRepository.save(califcation);
		//Enviando mail
		if(solicitud.getUsuarioDemandante().getMail() !=null){
			mailService.enviarMail(solicitud.getUsuarioDemandante().getMail(),"Notificación solicitud finalizada - SIOS","La solicitud ha sido finalizada. Recuerde calificar al oferente.");
		}

		//Guardando solicitud
		solicitudEstadoRepository.save(solicitudEstado);
		solicitud.getSolicitudEstados().add(solicitudEstado);
		solicitudRepository.save(solicitud);

		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

	@Override

	public List<SolicitudDemandanteModel> getSolicitudesEfectuadasPorUsuario(String idUsuario) {
		Usuario usuario=usuarioRepository.findUsuarioById(idUsuario);
		List<SolicitudDemandanteModel> solicitudModels= new ArrayList<>();
		for(Solicitud solicitud:solicitudRepository.findSolicitudByUsuarioDemandante(usuario)){
			SolicitudDemandanteModel solicitudDemandanteModel = new SolicitudDemandanteModel();
			solicitudDemandanteModel.setId(solicitud.getId());
			solicitudDemandanteModel.setDescripcion(solicitud.getDescripcion());
			solicitudDemandanteModel.setFechaSolicitud(solicitud.getFechaSolicitud());
			solicitudDemandanteModel.setUsuarioDemandante(solicitud.getUsuarioDemandante().getUsername());
			solicitudDemandanteModel.setUsuarioOferente(solicitud.getUsuarioOferente().getUsername());
			solicitudDemandanteModel.setNombreEstadoSolicitud(ultimoEstadoSolicitud(solicitud.getId())
					.getEstadoSolicitud().getNombreEstadoSolicitud());
			for(Archivo archivo : solicitud.getArchivos()) {
				solicitudDemandanteModel.getUrlArchivos().add(archivo.getUrlArchivo());
			}
			solicitudDemandanteModel.setEstaCalificada(estaCalificadaLaSolicitud(solicitud,usuario));
			solicitudModels.add(solicitudDemandanteModel);
		}
		return solicitudModels;
	}

	/**
	 * Retorna la cantidad de solicitudes en las que no calificó a su empleador.
	 * @param id
	 * @return
	 */
	@Override
	public int cantidadSolicitudesComoOferenteSinCalificar(String id) {
		Usuario usuario=usuarioRepository.findUsuarioById(id);
		//Filtrando las que están realizadas y devolviendo cantidad
		return  getSolicitudesRealizadas(solicitudRepository.findSolicitudByUsuarioOferente(usuario)).size();
	}

	/**
	 * Retorna la cantidad de solicitudes en las que no calificó al trabajador(oferente)
	 * @param id
	 * @return
	 */
	@Override
	public int cantidadSolicitudesComoDemandanteSinCalificar(String id) {
		Usuario usuario=usuarioRepository.findUsuarioById(id);
		return getSolicitudesRealizadas(solicitudRepository.findSolicitudByUsuarioDemandante(usuario)).size();
	}

	private List<Solicitud> getSolicitudesRealizadas(List<Solicitud> solicituds ){
		List<Solicitud> listaFiltrada=new ArrayList<>();
		EstadoSolicitud estadoSolicitud= estadoSolicitudRepository.findEstadoSolicitudByNombreEstadoSolicitud("Realizada");
		for(Solicitud solicitud:solicituds){
			for (SolicitudEstado solicitudEstado:solicitud.getSolicitudEstados()){
				if(solicitudEstado.isActivo() && solicitudEstado.getEstadoSolicitud().equals(estadoSolicitud)){
					listaFiltrada.add(solicitud);
					break;
				}
			}
		}
		return listaFiltrada;
	}

	private boolean estaCalificadaLaSolicitud(Solicitud solicitud,Usuario usuario){
		return calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud,usuario) != null ? true:false;
	}
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

	@Override
	public List<SolicitudCalificacionesModel> listCalificacionesByUsuario(String idUsuario) {
		List<SolicitudCalificacionesModel> solicitudCalificaciones = new ArrayList<>();
		for(Solicitud solicitud : solicitudRepository.findSolicitudByUsuarioOferente(usuarioRepository.findUsuarioById(idUsuario))) {
			for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
				if(solicitudEstado.isActivo()&&solicitudEstado.getEstadoSolicitud().equals("Finalizado")) {
					solicitudCalificaciones.add(solicitudConverter.convertSolicitudToSolicitudCalificacionesModel(solicitud));
				}
			}
		}
		return solicitudCalificaciones;
	}

	@Override
	public List<TrabajosOferenteModel> trabajosOferente(String idUsuario) {
		List<TrabajosOferenteModel> trabajosOferenteModels = new ArrayList<>();
		for(Solicitud solicitud : solicitudRepository.findSolicitudByUsuarioOferente(usuarioRepository.findUsuarioById(idUsuario))) {
			for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
				if(solicitudEstado.isActivo()&&solicitudEstado.getEstadoSolicitud().getNombreEstadoSolicitud().equals("Finalizada")) {
					trabajosOferenteModels.add(solicitudConverter.convertSolicitudToTrabajoOferenteModel(solicitud));
				}
			}
		}
		return trabajosOferenteModels;
	}

	@Override
	public Integer trabajosRealizados(String idUsuario) {
		Integer cantidad = 0;
		for(Solicitud solicitud : solicitudRepository.findSolicitudByUsuarioOferente(usuarioRepository.findUsuarioById(idUsuario))) {
			for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
				if(solicitudEstado.isActivo() && solicitudEstado.getEstadoSolicitud().getNombreEstadoSolicitud().equals("Finalizada")) {
					cantidad += 1;
				}
			}
		}
		return cantidad;
	}

	@Override
	public Integer trabajosEnCurso(String idUsuario) {
		Integer cantidad = 0;
		for(Solicitud solicitud : solicitudRepository.findSolicitudByUsuarioOferente(usuarioRepository.findUsuarioById(idUsuario))) {
			for(SolicitudEstado solicitudEstado : solicitud.getSolicitudEstados()) {
				if(solicitudEstado.isActivo() && solicitudEstado.getEstadoSolicitud().getNombreEstadoSolicitud().equals("Aceptada")) {
					cantidad += 1;
				}
			}
		}
		return cantidad;
	}

}
