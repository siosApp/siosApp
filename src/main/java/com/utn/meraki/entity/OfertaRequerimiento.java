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
@Table(name = "oferta_requerimiento")

public class OfertaRequerimiento {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "fecha_oferta")
	private Date fechaOferta;
	
	@Column(name = "asignado")
	private Boolean asignado;
	
	@Column(name = "respuesta")
	private String respuesta;
	
	@Column(name = "precio_ofertado")
	private Float precioOfertado;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_requerimiento")
	private Requerimiento requerimiento;
	
	//CONSTRUCTOR
	public OfertaRequerimiento() {
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getAsignado() {
		return asignado;
	}

	public void setAsignado(Boolean asignado) {
		this.asignado = asignado;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Float getPrecioOfertado() {
		return precioOfertado;
	}

	public void setPrecioOfertado(Float precioOfertado) {
		this.precioOfertado = precioOfertado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}

	public Date getFechaOferta() {
		return fechaOferta;
	}

	public void setFechaOferta(Date fechaOferta) {
		this.fechaOferta = fechaOferta;
	}
	
}
