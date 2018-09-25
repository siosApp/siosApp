package com.utn.meraki.model;

import java.util.Date;
import java.util.List;

public class UsuarioRubroModel {

	//ATRIBUTOS
    private String id;
    
    private Date fechaAsignacion;
    
    private List<CertificadoModel> certificados;
    
    private List<ExperienciaModel> experiencias;
    
    private RubroModel rubro;
    
    //CONSTRUCTOR
    public UsuarioRubroModel(){

    }

    //GET AND SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

   	public List<CertificadoModel> getCertificados() {
		return certificados;
	}

	public void setCertificados(List<CertificadoModel> certificados) {
		this.certificados = certificados;
	}

	public List<ExperienciaModel> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<ExperienciaModel> experiencias) {
		this.experiencias = experiencias;
	}

	public RubroModel getRubro() {
        return rubro;
    }

    public void setRubro(RubroModel rubro) {
        this.rubro = rubro;
    }
}
