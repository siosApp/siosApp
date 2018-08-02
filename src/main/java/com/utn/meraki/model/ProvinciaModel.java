package com.utn.meraki.model;

import java.sql.Date;

public class ProvinciaModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreProvincia;
	
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public ProvinciaModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
}
