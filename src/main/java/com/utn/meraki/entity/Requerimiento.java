package com.utn.meraki.entity;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
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

@Entity
@Table(name = "requerimientos")

public class Requerimiento {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "fecha_publicacion")
	private Date fechaPublicacion;
	
	@Column(name = "precio_a_pagar")
	private Integer precioApagar;
	
	@Column(name = "tiempo_estimado")
	private Integer tiempoEstimado;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estado")
	private EstadoRequerimiento estadoRequerimiento;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_rubro")
	private Rubro rubro;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_requerimiento")
	private List<Archivo> archivos = new ArrayList<>();
	
	//CONSTRUCTOR
	public Requerimiento() {
	}

	//GET AND SET
	public Integer getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(Integer tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Integer getPrecioApagar() {
		return precioApagar;
	}

	public void setPrecioApagar(Integer precioApagar) {
		this.precioApagar = precioApagar;
	}

	public EstadoRequerimiento getEstadoRequerimiento() {
		return estadoRequerimiento;
	}

	public void setEstadoRequerimiento(EstadoRequerimiento estadoRequerimiento) {
		this.estadoRequerimiento = estadoRequerimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	
}
