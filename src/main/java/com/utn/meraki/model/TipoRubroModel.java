package com.utn.meraki.model;

import java.util.Date;

public class TipoRubroModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreTipoRubro;
	
	private Date fechaBaja;
	
	private String descripcion;
	
	//CONSTRUCTOR
	public TipoRubroModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreTipoRubro() {
		return nombreTipoRubro;
	}

	public void setNombreTipoRubro(String nombreTipoRubro) {
		this.nombreTipoRubro = nombreTipoRubro;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
