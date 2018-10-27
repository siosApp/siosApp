package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class TrabajosOferenteModel {
	
	//ATRIBUTOS
	private String descripcion;
	
	private String nombreRubro;
	
	private String mailDemandante;
	
	private List<String> comentarios = new ArrayList<>();
	
	//CONSTRUCTOR
	public TrabajosOferenteModel() {
		
	}

	//GET AND SET
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public String getMailDemandante() {
		return mailDemandante;
	}

	public void setMailDemandante(String mailDemandante) {
		this.mailDemandante = mailDemandante;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}
}
