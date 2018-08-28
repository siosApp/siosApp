package com.utn.meraki.entity;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "medios_pagos")

public class MedioPago {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_medio_pago")
	private String nombreMedioPago;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public MedioPago() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreMedioPago() {
		return nombreMedioPago;
	}

	public void setNombreMedioPago(String nombreMedioPago) {
		this.nombreMedioPago = nombreMedioPago;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
}
