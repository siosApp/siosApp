package com.utn.meraki.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RequerimientoModel {
	
	//ATRIBUTOS
	private String id;
	
	private String titulo;
		
	private String descripcion;
	
	private Date fechaPublicacion;
	
	private Integer precioApagar;
	
	private String nombreEstadoRequerimiento;
	
	private String idUsuario;
	
	private int tiempoEstimado;
	
	private String nombreRubro;
	
	private List<String> urlArchivos = new ArrayList<>();
	
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

	public Integer getPrecioApagar() {
		return precioApagar;
	}

	public void setPrecioApagar(Integer precioApagar) {
		this.precioApagar = precioApagar;
	}

	public String getNombreEstadoRequerimiento() {
		return nombreEstadoRequerimiento;
	}

	public void setNombreEstadoRequerimiento(String nombreEstadoRequerimiento) {
		this.nombreEstadoRequerimiento = nombreEstadoRequerimiento;
	}

	public List<String> getUrlArchivos() {
		return urlArchivos;
	}

	public void setUrlArchivos(List<String> urlArchivos) {
		this.urlArchivos = urlArchivos;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}
	
	

}
