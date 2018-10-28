package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class CalificacionUsuarioModel {
	
	//ATRIBUTOS
	private Integer cantidadEstrella;
	
	private Integer cantidadVotos;
	
	private List<String> usernameUsuarios = new ArrayList<>();
	
	private List<CalificacionRecibidaModel> datosUsuarios = new ArrayList<>();
	
	//CONSTRUCTOR
	public CalificacionUsuarioModel() {
		
	}

	//GET AND SET
	public Integer getCantidadEstrella() {
		return cantidadEstrella;
	}

	public void setCantidadEstrella(Integer cantidadEstrella) {
		this.cantidadEstrella = cantidadEstrella;
	}

	public Integer getCantidadVotos() {
		return cantidadVotos;
	}

	public void setCantidadVotos(Integer cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}

	public List<String> getUsernameUsuarios() {
		return usernameUsuarios;
	}

	public void setUsernameUsuarios(List<String> usernameUsuarios) {
		this.usernameUsuarios = usernameUsuarios;
	}

	public List<CalificacionRecibidaModel> getDatosUsuarios() {
		return datosUsuarios;
	}

	public void setDatosUsuarios(List<CalificacionRecibidaModel> datosUsuarios) {
		this.datosUsuarios = datosUsuarios;
	}
	
}
