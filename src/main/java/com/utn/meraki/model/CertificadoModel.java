package com.utn.meraki.model;

import java.sql.Date;

public class CertificadoModel {
	
	//ATRIBUTOS
	private String id;
	
	private String nombreCertificado;
	
	private Date fechaCertificado;

	private String idAdjunto;
	//CONSTRUCTOR
	public CertificadoModel() {
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

	public String getIdAdjunto() {
		return idAdjunto;
	}

	public void setIdAdjunto(String idAdjunto) {
		this.idAdjunto = idAdjunto;
	}
}
