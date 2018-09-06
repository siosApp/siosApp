package com.utn.meraki.model;

public class FiltroModel {
	
	//ATRIBUTOS
	private String nombreRubro;
	
	private String nombreTipoRubro;
	
	private String nombreLocalidad;
	
	private String nombreDepartamento;
	
	private String nombreProvincia;
	
	//CONSTRUCTOR
	public FiltroModel() {
		
	}

	//GET AND SET
	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public String getNombreTipoRubro() {
		return nombreTipoRubro;
	}

	public void setNombreTipoRubro(String nombreTipoRubro) {
		this.nombreTipoRubro = nombreTipoRubro;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	
}
