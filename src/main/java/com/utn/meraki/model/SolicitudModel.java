package com.utn.meraki.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.utn.meraki.entity.Archivo;

public class SolicitudModel {
	
	//ATRIBUTOS
	private String id;
	
	private Date fechaSolicitud;
	
	private String descripcion;
	
	private String nombreEstadoSolicitud;
	
	private List<String> urlArchivos = new ArrayList<>();
	
	private String usuarioOferente;
	
	private String usuarioDemandante;
	
	//CONSTRUCTOR
	public SolicitudModel() {
		
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNombreEstadoSolicitud() {
		return nombreEstadoSolicitud;
	}

	public void setNombreEstadoSolicitud(String nombreEstadoSolicitud) {
		this.nombreEstadoSolicitud = nombreEstadoSolicitud;
	}

	public List<String> getUrlArchivos() {
		return urlArchivos;
	}

	public void setUrlArchivos(List<String> urlArchivos) {
		this.urlArchivos = urlArchivos;
	}

	public String getUsuarioOferente() {
		return usuarioOferente;
	}

	public void setUsuarioOferente(String usuarioOferente) {
		this.usuarioOferente = usuarioOferente;
	}

	public String getUsuarioDemandante() {
		return usuarioDemandante;
	}

	public void setUsuarioDemandante(String usuarioDemandante) {
		this.usuarioDemandante = usuarioDemandante;
	}
	
}
