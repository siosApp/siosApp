package com.utn.meraki.converter;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.Calificacion;
import com.utn.meraki.entity.Comentario;
import com.utn.meraki.model.CalificacionModel;
import com.utn.meraki.repository.ComentarioRepository;
import com.utn.meraki.repository.SolicitudRepository;
import com.utn.meraki.repository.UsuarioRepository;

@Component("calificacionConverter")

public class CalificacionConverter {
	
	//REPOSITORY
	@Autowired
	SolicitudRepository solicitudRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ComentarioRepository comentarioRepository;
	
	public Calificacion convertCalificacionModelToCalificacion(CalificacionModel calificacionModel) {
		Calificacion calificacion = new Calificacion();
		calificacion.setFechaCalificacion(new Date(System.currentTimeMillis()));
		calificacion.setCalificacion(calificacionModel.getCalificacion());
		calificacion.setSolicitud(solicitudRepository.findSolicitudById(calificacionModel.getIdSolicitud()));
		calificacion.setUsuario(usuarioRepository.findUsuarioByUsername(calificacionModel.getUsername()));
		Comentario comentario = new Comentario(calificacionModel.getComentario());
		comentarioRepository.save(comentario);
		calificacion.getComentarios().add(comentario);
		return calificacion;
	}
	
	public CalificacionModel convertCalificacionToCalificacionModel(Calificacion calificacion) {
		CalificacionModel calificacionModel = new CalificacionModel();
		calificacionModel.setCalificacion(calificacion.getCalificacion());
		calificacionModel.setFechaCalificacion(calificacion.getFechaCalificacion());
		calificacionModel.setUsername(calificacion.getUsuario().getUsername());
		calificacionModel.setIdSolicitud(calificacion.getSolicitud().getId());
		//calificacionModel.setComentario(calificacion.getComentarios());
		return calificacionModel;
	}

}