package com.utn.meraki.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "certificados")

public class Certificado {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre_certificado")
	private String nombreCertificado;
	
	@Column(name = "fecha_certificado")
	private Date fechaCertificado;
		
	//CONSTRUCTOR
	public Certificado() {
	}

	public Certificado(String nombreCertificado, Date fechaCertificado) {
		super();
		this.nombreCertificado = nombreCertificado;
		this.fechaCertificado = fechaCertificado;
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreCertificado() {
		return nombreCertificado;
	}

	public void setNombreCertificado(String nombreCertificado) {
		this.nombreCertificado = nombreCertificado;
	}

	public Date getFechaCertificado() {
		return fechaCertificado;
	}

	public void setFechaCertificado(Date fechaCertificado) {
		this.fechaCertificado = fechaCertificado;
	}
	
}
