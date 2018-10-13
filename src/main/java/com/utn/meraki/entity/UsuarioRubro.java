package com.utn.meraki.entity;

import java.util.Date;
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
@Table(name = "usuarios_rubros")

public class UsuarioRubro {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "fecha_asignacion")
	private Date fechaAsignacion;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_usuario_rubro")
	private List<Certificado> certificados = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_usuario_rubro")
	private List<Experiencia> experiencias = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_rubro")
	private Rubro rubro;
	
	//CONSTRUCTOR
	public UsuarioRubro() {
	}

	public UsuarioRubro(Date fechaAsignacion, List<Certificado> certificados, List<Experiencia> experiencias,
			Rubro rubro) {
		super();
		this.fechaAsignacion = fechaAsignacion;
		this.certificados = certificados;
		this.experiencias = experiencias;
		this.rubro = rubro;
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

	public List<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(List<Certificado> certificados) {
		this.certificados = certificados;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public void addCertificado(Certificado certificado){
		certificados.add(certificado);
	}
	public void eliminarCertificado(Certificado certificado){
		certificados.remove(certificado);
	}
	public void addExperiencia(Experiencia experiencia){
		experiencias.add(experiencia);
	}
	public void eliminarExperiencia(Experiencia experiencia){
		experiencias.remove(experiencia);
	}
	public boolean existeCertificado(Certificado certificado){
		return certificados.contains(certificado);
	}
	public boolean existeExperiencia(Experiencia experiencia){
		return  experiencias.contains(experiencia);
	}
	public Experiencia getPrimerExperiencia(){
		if (!experiencias.isEmpty()){
			return experiencias.get(0);
		}
		return new Experiencia();
	}
}
