package com.utn.meraki.model;

public class RubroMasOfrecidoModel {
	
	//ATRIBUTOS
	private String nombreRubro;
		
	private Integer cantidadSolicitudes;
	
	//CONSTRUCTORES
	public RubroMasOfrecidoModel() {
		
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public Integer getCantidadSolicitudes() {
		return cantidadSolicitudes;
	}

	public void setCantidadSolicitudes(Integer cantidadSolicitudes) {
		this.cantidadSolicitudes = cantidadSolicitudes;
	}
	
}
