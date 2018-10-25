package com.utn.meraki.entity;

import java.sql.Date;

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
@Table(name = "destacados")

public class Destacado {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "monto")
	private Double monto;
	
	@Column(name = "fecha_destacado")
	private Date fechaDestacado;
	
	@Column(name = "fecha_vto_destacado")
	private Date fechaVtoDestacado;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estado")
	private EstadoDestacado estadoDestacado;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_medio_pago")
	private MedioPago medioPago;
	
	//CONSTRUCTOR
	public Destacado() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFechaDestacado() {
		return fechaDestacado;
	}

	public void setFechaDestacado(Date fechaDestacado) {
		this.fechaDestacado = fechaDestacado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EstadoDestacado getEstadoDestacado() {
		return estadoDestacado;
	}

	public void setEstadoDestacado(EstadoDestacado estadoDestacado) {
		this.estadoDestacado = estadoDestacado;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Date getFechaVtoDestacado() {
		return fechaVtoDestacado;
	}

	public void setFechaVtoDestacado(Date fechaVtoDestacado) {
		this.fechaVtoDestacado = fechaVtoDestacado;
	}
	
}
