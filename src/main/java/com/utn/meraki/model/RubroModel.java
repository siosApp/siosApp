package com.utn.meraki.model;

import java.util.Date;

public class RubroModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreRubro;
	
	private Date fechaBaja;
	
	private String descripcion;
	
	private String nombreTipoRubro;
	
	//CONSTRUCTOR
	public RubroModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
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

	public String getNombreTipoRubro() {
		return nombreTipoRubro;
	}

	public void setNombreTipoRubro(String nombreTipoRubro) {
		this.nombreTipoRubro = nombreTipoRubro;
	}

}
