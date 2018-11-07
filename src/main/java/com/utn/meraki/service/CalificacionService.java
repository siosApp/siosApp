package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.CalificacionModel;
import com.utn.meraki.model.CalificacionUsuarioModel;

public interface CalificacionService {
	
	public abstract CalificacionModel realizarCalificacion(CalificacionModel calificacionModel);
	
	public List<CalificacionUsuarioModel> calificacionesUsuario(String idUsuario);
	
	public Integer promedioCalificacionByUsuario(String idUsuario);

}
