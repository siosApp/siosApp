package com.utn.meraki.model;

import java.sql.Date;

public class RequerimientoModel {
	
	//ATRIBUTOS
	private String id;
	
	private String titulo;
		
	private String descripcion;
	
	private Date fechaPublicacion;
	
	private Float precioApagar;
	
	private String nombreEstadoRequerimiento;
	
	private String idUsuario;
	
	private int tiempoEstimado;
	
	//private List<Archivo> archivos = new ArrayList<>();
	
	public int getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	//CONSTRUCTOR
	public RequerimientoModel() {
		
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Float getPrecioApagar() {
		return precioApagar;
	}

	public void setPrecioApagar(Float precioApagar) {
		this.precioApagar = precioApagar;
	}

	public String getNombreEstadoRequerimiento() {
		return nombreEstadoRequerimiento;
	}

	public void setNombreEstadoRequerimiento(String nombreEstadoRequerimiento) {
		this.nombreEstadoRequerimiento = nombreEstadoRequerimiento;
	}

	
	
}
