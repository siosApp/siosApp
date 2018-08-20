package com.utn.meraki.model;

import java.util.Date;

public class TipoUsuarioModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreTipoUsuario;
	
	private Date fechaBaja;
	
	private String descripcion;
	
	//CONSTRUCTOR
	public TipoUsuarioModel() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}

	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
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
