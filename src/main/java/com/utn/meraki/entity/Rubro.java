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
@Table(name = "rubros")

public class Rubro {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_rubro")
	private String nombreRubro;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_tipo_rubro")
	private TipoRubro tipoRubro;
	
	//CONSTRUCTOR
	public Rubro() {
	}

	public Rubro(String nombreRubro, Date fechaBaja, String descripcion, TipoRubro tipoRubro) {
		super();
		this.nombreRubro = nombreRubro;
		this.fechaBaja = fechaBaja;
		this.descripcion = descripcion;
		this.tipoRubro = tipoRubro;
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
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

	public TipoRubro getTipoRubro() {
		return tipoRubro;
	}

	public void setTipoRubro(TipoRubro tipoRubro) {
		this.tipoRubro = tipoRubro;
	}
	
}
