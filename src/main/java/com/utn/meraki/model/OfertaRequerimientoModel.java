package com.utn.meraki.model;

public class OfertaRequerimientoModel {
	
	//ATRIBUTOS
	private String id;
	
	private boolean asignado;
	
	private float precioOfertado;
	
	private String respuesta;
	
	private String idRequerimiento;
	
	private String idUsuario;
	
	//CONSTRUCTOR
	public OfertaRequerimientoModel() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAsignado() {
		return asignado;
	}

	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}

	public float getPrecioOfertado() {
		return precioOfertado;
	}

	public void setPrecioOfertado(float precioOfertado) {
		this.precioOfertado = precioOfertado;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(String idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
