package com.utn.meraki.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "solicitudes")

public class Solicitud {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "fecha_solicitud")
	private Date fechaSolicitud;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_solicitud")
	private List<SolicitudEstado> solicitudEstados = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_solicitud")
	private List<Archivo> archivos = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_oferente")
	private Usuario usuarioOferente;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_demandante")
	private Usuario usuarioDemandante;
	
	//CONSTRUCTOR
	public Solicitud() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SolicitudEstado> getSolicitudEstados() {
		return solicitudEstados;
	}

	public void setSolicitudEstados(List<SolicitudEstado> solicitudEstados) {
		this.solicitudEstados = solicitudEstados;
	}

	public Usuario getUsuarioOferente() {
		return usuarioOferente;
	}

	public void setUsuarioOferente(Usuario usuarioOferente) {
		this.usuarioOferente = usuarioOferente;
	}

	public Usuario getUsuarioDemandante() {
		return usuarioDemandante;
	}

	public void setUsuarioDemandante(Usuario usuarioDemandante) {
		this.usuarioDemandante = usuarioDemandante;
	}

	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}
	
}
