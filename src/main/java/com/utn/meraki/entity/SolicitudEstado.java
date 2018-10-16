package com.utn.meraki.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "solicitudes_estados")

public class SolicitudEstado implements Comparable<SolicitudEstado>{
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "fecha_cambio_estado")
	private Date fechaCambioEstado;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estado_solicitud")
	private EstadoSolicitud estadoSolicitud;
	
	//CONSTRUCTOR
	public SolicitudEstado() {
	}

	public SolicitudEstado(Date fechaCambioEstado, EstadoSolicitud estadoSolicitud) {
		super();
		this.fechaCambioEstado = fechaCambioEstado;
		this.estadoSolicitud = estadoSolicitud;
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaCambioEstado() {
		return fechaCambioEstado;
	}

	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}

	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	@Override
	public int compareTo(SolicitudEstado o) {
		return getFechaCambioEstado().compareTo(o.getFechaCambioEstado());
	}
}
