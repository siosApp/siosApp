package com.utn.meraki.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tipos_usuarios")

public class TipoUsuario {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_tipo_usuario")
	private String nombreTipoUsuario;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	//CONTRUCTOR
	public TipoUsuario() {
	}

	public TipoUsuario(String nombreTipoUsuario, Date fechaBaja, String descripcion) {
		super();
		this.nombreTipoUsuario = nombreTipoUsuario;
		this.fechaBaja = fechaBaja;
		this.descripcion = descripcion;
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}

	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
