package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitudTerminadaModel {
	
	//ATRIBUTOS
	public String idSolicitud;
	
	public Date fechaSolicitud;
	
	public String descripcion;
	
	public String usernameOferente;
	
	public String usernameDemandante;
	
	public List<CalificacionModel> calificaciones = new ArrayList<>();
	
	public Date fechaFinalizacion;
	
	//CONSTRUCTOR
	public SolicitudTerminadaModel() {
		
	}

	//GET AND SET
	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsernameOferente() {
		return usernameOferente;
	}

	public void setUsernameOferente(String usernameOferente) {
		this.usernameOferente = usernameOferente;
	}

	public String getUsernameDemandante() {
		return usernameDemandante;
	}

	public void setUsernameDemandante(String usernameDemandante) {
		this.usernameDemandante = usernameDemandante;
	}

	public List<CalificacionModel> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionModel> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
}
