package com.utn.meraki.model;

import java.util.Date;

public class EstadoDestacadoModel {

	//ATRIBUTOS
	private String id;
	
	private String nombreEstadoDestacado;
	
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public EstadoDestacadoModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreEstadoDestacado() {
		return nombreEstadoDestacado;
	}

	public void setNombreEstadoDestacado(String nombreEstadoDestacado) {
		this.nombreEstadoDestacado = nombreEstadoDestacado;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}
	
	

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
}
