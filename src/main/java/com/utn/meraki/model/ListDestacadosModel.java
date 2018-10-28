package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class ListDestacadosModel {
	
	//ATRIBUTOS
	private Integer cantidadDestacados;
	
	private List<UsuarioModel> usuariosDestacados = new ArrayList<>();
	
	//CONSTRUCTOR
	public ListDestacadosModel() {
		
	}

	public Integer getCantidadDestacados() {
		return cantidadDestacados;
	}

	public void setCantidadDestacados(Integer cantidadDestacados) {
		this.cantidadDestacados = cantidadDestacados;
	}

	public List<UsuarioModel> getUsuariosDestacados() {
		return usuariosDestacados;
	}

	public void setUsuariosDestacados(List<UsuarioModel> usuariosDestacados) {
		this.usuariosDestacados = usuariosDestacados;
	}
	
}
