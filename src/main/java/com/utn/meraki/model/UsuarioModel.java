package com.utn.meraki.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioModel {
    //ATRIBUTOS

    private String id;

    private String nombre;

    private String apellido;

    private Date fechaBaja;

    private Date fechaNacimiento;

    private Date fechaUltIngreso;

    private String mail;

    private boolean oferente;

    private String username;

    private String password;

    private String sexo;

    private DomicilioModel domicilio;

    private String tipoUsuario;

    private List<UsuarioRubroModel> usuarioRubros = new ArrayList<>();

    //CONSTRUCTOR
    public UsuarioModel() {
    }

    //GET AND SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaUltIngreso() {
        return fechaUltIngreso;
    }

    public void setFechaUltIngreso(Date fechaUltIngreso) {
        this.fechaUltIngreso = fechaUltIngreso;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getOferente() {
        return oferente;
    }

    public void setOferente(Boolean oferente) {
        this.oferente = oferente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<UsuarioRubroModel> getUsuarioRubros() {
        return usuarioRubros;
    }

    public DomicilioModel getDomicilio() {
        return domicilio;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setDomicilio(DomicilioModel domicilio) {
        this.domicilio = domicilio;
    }

    public void setOferente(boolean oferente) {
        this.oferente = oferente;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setUsuarioRubros(List<UsuarioRubroModel> usuarioRubros) {
        this.usuarioRubros = usuarioRubros;
    }
}
