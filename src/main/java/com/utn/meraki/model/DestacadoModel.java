package com.utn.meraki.model;

import java.sql.Date;

public class DestacadoModel {
	
	//ATRIBUTOS
	private String id;
	
	private Double monto;
	
	private Date fechaDestacado;
	
	private Date fechaVtoDestacado;
	
	private String nombreUsuario;
	
	private String nombreEstado;
	
	private String nombreMedioPago;

	private String nombre;

	private String apellido;

	private String idUsuario;
	
	//CONSTRUCTOR
	public DestacadoModel() {
		
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFechaDestacado() {
		return fechaDestacado;
	}

	public void setFechaDestacado(Date fechaDestacado) {
		this.fechaDestacado = fechaDestacado;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getNombreMedioPago() {
		return nombreMedioPago;
	}

	public void setNombreMedioPago(String nombreMedioPago) {
		this.nombreMedioPago = nombreMedioPago;
	}

	public Date getFechaVtoDestacado() {
		return fechaVtoDestacado;
	}

	public void setFechaVtoDestacado(Date fechaVtoDestacado) {
		this.fechaVtoDestacado = fechaVtoDestacado;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}
