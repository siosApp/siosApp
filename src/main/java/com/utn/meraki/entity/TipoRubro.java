package com.utn.meraki.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tipos_rubros")

public class TipoRubro {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_tipo_rubro")
	private String nombreTipoRubro;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	//CONSTRUCTOR
	public TipoRubro() {
	}

	public TipoRubro(String nombreTipoRubro, Date fechaBaja, String descripcion) {
		super();
		this.nombreTipoRubro = nombreTipoRubro;
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

	public String getNombreTipoRubro() {
		return nombreTipoRubro;
	}

	public void setNombreTipoRubro(String nombreTipoRubro) {
		this.nombreTipoRubro = nombreTipoRubro;
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
