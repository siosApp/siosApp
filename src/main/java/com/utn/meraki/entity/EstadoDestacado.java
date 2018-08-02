package com.utn.meraki.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "estados_destacados")

public class EstadoDestacado {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_estado_destacado")
	private String nombreEstadoDestacado;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public EstadoDestacado() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreEstadoDestacado() {
		return nombreEstadoDestacado;
	}

	public void setNombreEstadoDestacado(String nombreEstadoDestacado) {
		this.nombreEstadoDestacado = nombreEstadoDestacado;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
}
