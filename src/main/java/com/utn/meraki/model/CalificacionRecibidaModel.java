package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class CalificacionRecibidaModel {
	
	//ATRIBUTOS
	private String username;
	
	private String descripcionTrabajo;
	
	private List<String> comentarios = new ArrayList<>();
	
	private String nombreRubro;
	
	private Integer calificacion;

	private String idUsuario;

	//CONSTRUCTOR
	public CalificacionRecibidaModel() {
		
	}

	//GET AND SET
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}

	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}
