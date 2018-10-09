package com.utn.meraki.model;

public class UsuariosByRubro {
	
	//ATRIBUTOS
	public String nombreRubro;
	
	public Integer cantidadUsuarios;
	
	//CONSTRUCTOR
	public UsuariosByRubro() {
		
	}

	//GET AND SET
	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public Integer getCantidadUsuarios() {
		return cantidadUsuarios;
	}

	public void setCantidadUsuarios(Integer cantidadUsuarios) {
		this.cantidadUsuarios = cantidadUsuarios;
	}
	
	

}
