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
@Table(name = "calificaciones")

public class Calificacion {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "calificacion")
	private Integer calificacion;
	
	@Column(name = "fecha_calificacion")
	private Date fechaCalificacion;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_calificacion")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_solicitud")
	private Solicitud solicitud;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	//CONSTRUCTOR
	public Calificacion() {
	}

	public Calificacion(Integer calificacion, Date fechaCalificacion, List<Comentario> comentarios, Solicitud solicitud,
			Usuario usuario) {
		super();
		this.calificacion = calificacion;
		this.fechaCalificacion = fechaCalificacion;
		this.comentarios = comentarios;
		this.solicitud = solicitud;
		this.usuario = usuario;
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaCalificacion() {
		return fechaCalificacion;
	}

	public void setFechaCalificacion(Date fechaCalificacion) {
		this.fechaCalificacion = fechaCalificacion;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
