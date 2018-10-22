package com.utn.meraki.model;

import java.util.Date;
import java.util.List;

public class SolicitudCalificacionesModel {
	
	//ATRIBUTOS
	private String nombreUsuario;
	
	private String nombreApellido;
	
	private String mail;
	
	private Date fechaSolicitud;
	
	private String descripcion;
	
	private Integer calificacion;
	
	private List<String> comentarios;
	
	private Date fechaFinalizacion;
	
	//CONSTRUCTOR
	public SolicitudCalificacionesModel() {
		
	}

	//GET AND SET
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
}
