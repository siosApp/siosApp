package com.utn.meraki.model;

import java.util.Date;

public class LocalidadModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreLocalidad;
	
	private Date fechaBaja;
	
	private DepartamentoModel departamento;
	
	//CONSTRUCTOR
	public LocalidadModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public DepartamentoModel getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoModel departamento) {
		this.departamento = departamento;
	}
	
}
