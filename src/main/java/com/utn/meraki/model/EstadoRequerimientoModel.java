package com.utn.meraki.model;

import java.sql.Date;

public class EstadoRequerimientoModel {

	// ATRIBUTOS
	private String id;

	private String nombreEstado;

	private Date fechaBaja;

	// CONSTRUCTOR
	public EstadoRequerimientoModel() {
		super();
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

}