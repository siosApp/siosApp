package com.utn.meraki.model;

import java.util.Date;

public class MedioPagoModel {

	//ATRIBUTOS
	private String id;
	
	private String nombreMedioPago;
	
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public MedioPagoModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreMedioPago() {
		return nombreMedioPago;
	}

	public void setNombreMedioPago(String nombreMedioPago) {
		this.nombreMedioPago = nombreMedioPago;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}
	
	

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
}
