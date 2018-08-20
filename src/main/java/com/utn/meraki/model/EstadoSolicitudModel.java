package com.utn.meraki.model;

import java.util.Date;

public class EstadoSolicitudModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreEstadoSolicitud;
	
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public EstadoSolicitudModel() {
	}
	
	public EstadoSolicitudModel(String nombreEstadoSolicitud, Date fechaBaja) {
		super();
		this.nombreEstadoSolicitud = nombreEstadoSolicitud;
		this.fechaBaja = fechaBaja;
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreEstadoSolicitud() {
		return nombreEstadoSolicitud;
	}

	public void setNombreEstadoSolicitud(String nombreEstadoSolicitud) {
		this.nombreEstadoSolicitud = nombreEstadoSolicitud;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
}
