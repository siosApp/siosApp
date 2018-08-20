package com.utn.meraki.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "estados_solicitudes")

public class EstadoSolicitud {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_estado_solicitud")
	private String nombreEstadoSolicitud;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public EstadoSolicitud() {
	}
	
	public EstadoSolicitud(String nombreEstadoSolicitud, Date fechaBaja) {
		super();
		this.nombreEstadoSolicitud = nombreEstadoSolicitud;
		this.fechaBaja = fechaBaja;
	}

	//GET AND SET
	public String getNombreEstadoSolicitud() {
		return nombreEstadoSolicitud;
	}

	public void setNombreEstadoSolicitud(String nombreEstadoSolicitud) {
		this.nombreEstadoSolicitud = nombreEstadoSolicitud;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
}


