package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioRubroModel {

    private String id;
    private Date fechaAsignacion;
    private List<CertificadoModel> certificados=new ArrayList<>();
    private List<ExperienciaModel> experiencias=new ArrayList<>();
    private RubroModel rubro;

    public UsuarioRubroModel(){

    }

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
