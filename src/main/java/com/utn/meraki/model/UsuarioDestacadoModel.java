package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDestacadoModel implements Comparable<UsuarioDestacadoModel>{
	
	//ATRIBUTOS
	private String idUsuario;
	
	private String nombre;
	
	private String apellido;
	
	private DomicilioModel domicilio;
	
	private List<RubroModel> rubros = new ArrayList<>();

	private String experiencia;

	private String imagen;

	private String username;
	
	private Boolean destacado;

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

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getImagen() {
		return imagen;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getDestacado() {
		return destacado;
	}

	public void setDestacado(Boolean destacado) {
		this.destacado = destacado;
	}
	
	@Override
	public int compareTo(UsuarioDestacadoModel o) {
		 String a=new String(String.valueOf(this.getDestacado())+this.getUsername());
	     String b=new String(String.valueOf(o.getDestacado())+o.getUsername());
	     return b.compareTo(a);
	}
	
}
