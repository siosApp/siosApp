package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDestacadoModel {
	
	//ATRIBUTOS
	private String idUsuario;
	
	private String nombre;
	
	private String apellido;
	
	private DomicilioModel domicilio;
	
	private List<RubroModel> rubros = new ArrayList<>();
	
	//CONSTRUCTOR
	public UsuarioDestacadoModel() {
		
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<RubroModel> getRubros() {
		return rubros;
	}

	public void setRubros(List<RubroModel> rubros) {
		this.rubros = rubros;
	}

	public DomicilioModel getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioModel domicilio) {
		this.domicilio = domicilio;
	}

}
