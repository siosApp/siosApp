package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.CalificacionConverter;
import com.utn.meraki.entity.Calificacion;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.model.CalificacionModel;
import com.utn.meraki.model.CalificacionUsuarioModel;
import com.utn.meraki.repository.CalificacionRepository;
import com.utn.meraki.repository.SolicitudRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.service.CalificacionService;

@Service("calificacionService")

public class CalificacionServiceImpl implements CalificacionService{

	//REPOSITORY
	@Autowired
	CalificacionRepository calificacionRepository;
	@Autowired
	SolicitudRepository solicitudRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
		
	//CONVERTER
	@Autowired
	CalificacionConverter calificacionConverter;
	
	@Override
	public CalificacionModel realizarCalificacion(CalificacionModel calificacionModel) {
		Calificacion calificacion = calificacionConverter.convertCalificacionModelToCalificacion(calificacionModel);
		calificacionRepository.save(calificacion);
		return calificacionConverter.convertCalificacionToCalificacionModel(calificacion);
	}

	@Override //ME MUESTRA TODAS LAS CALIFICACIONES POR ESTRELLAS RECIBIDAS DE UN USUARIO
	public List<CalificacionUsuarioModel> calificacionesUsuario(String idUsuario) {
		List<CalificacionUsuarioModel> calificaciones = new ArrayList<>();
		Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
		//Creo modelos
		CalificacionUsuarioModel calificacionUno = new CalificacionUsuarioModel();
		calificacionUno.setCantidadEstrella(1);
		CalificacionUsuarioModel calificacionDos = new CalificacionUsuarioModel();
		calificacionDos.setCantidadEstrella(2);
		CalificacionUsuarioModel calificacionTres = new CalificacionUsuarioModel();
		calificacionTres.setCantidadEstrella(3);
		CalificacionUsuarioModel calificacionCuatro = new CalificacionUsuarioModel();
		calificacionCuatro.setCantidadEstrella(4);
		CalificacionUsuarioModel calificacionCinco = new CalificacionUsuarioModel();
		calificacionCinco.setCantidadEstrella(5);
		//Inicializo cantidades de votos
		Integer votoUno = 0;
		Integer votoDos = 0;
		Integer votoTres = 0;
		Integer votoCuatro = 0;
		Integer votoCinco = 0;
		for(Solicitud solicitud : solicitudRepository.findSolicitudByUsuarioOferente(usuario)) {
			if(!calificacionRepository.findCalificacionBySolicitud(solicitud).isEmpty()) {
				System.out.println("La solicitud tiene calificaciones realizadas");
				if(calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud, usuario).getCalificacion()==1) {
					votoUno += 1;
					calificacionUno.getUsernameUsuarios().add(usuario.getUsername());
					calificacionUno.getDatosUsuarios().add(calificacionConverter.convertSolicitudToCalificacionRecibidaModel(solicitud));
				}else if(calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud, usuario).getCalificacion()==2) {
					votoDos += 1;
					calificacionDos.getUsernameUsuarios().add(usuario.getUsername());
					calificacionDos.getDatosUsuarios().add(calificacionConverter.convertSolicitudToCalificacionRecibidaModel(solicitud));
				}else if(calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud, usuario).getCalificacion()==3) {
					votoTres += 1;
					calificacionTres.getUsernameUsuarios().add(usuario.getUsername());
					calificacionTres.getDatosUsuarios().add(calificacionConverter.convertSolicitudToCalificacionRecibidaModel(solicitud));
				}else if(calificacionRepository.findCalificacionBySolicitudAndUsuario(solicitud, usuario).getCalificacion()==4) {
					votoCuatro += 1;
					calificacionCuatro.getUsernameUsuarios().add(usuario.getUsername());
					calificacionCuatro.getDatosUsuarios().add(calificacionConverter.convertSolicitudToCalificacionRecibidaModel(solicitud));
				}else {
					votoCinco += 1;
					calificacionCinco.getUsernameUsuarios().add(usuario.getUsername());
					calificacionCinco.getDatosUsuarios().add(calificacionConverter.convertSolicitudToCalificacionRecibidaModel(solicitud));
				}
			}
		}
		calificacionUno.setCantidadVotos(votoUno);
		calificaciones.add(calificacionUno);
		calificacionDos.setCantidadVotos(votoDos);
		calificaciones.add(calificacionDos);
		calificacionTres.setCantidadVotos(votoTres);
		calificaciones.add(calificacionTres);
		calificacionCuatro.setCantidadVotos(votoCuatro);
		calificaciones.add(calificacionCuatro);
		calificacionCinco.setCantidadVotos(votoCinco);
		calificaciones.add(calificacionCinco);
		return calificaciones;
	}

	
}
