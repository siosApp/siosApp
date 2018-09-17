package com.utn.meraki.model;

import java.util.Date;

public class DepartamentoModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreDepartamento;
	
	private Date fechaBaja;
	
	private ProvinciaModel provincia;
	
	//CONSTRUCTRO
	public DepartamentoModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public ProvinciaModel getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaModel provincia) {
		this.provincia = provincia;
	}
	
}
