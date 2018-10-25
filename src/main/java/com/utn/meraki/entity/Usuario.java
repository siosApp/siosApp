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
import sun.print.resources.serviceui_zh_TW;

@Entity
@Table(name = "usuarios")

public class Usuario {
	
	//ATRIBUTOS
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "fecha_ult_ingreso")
	private Date fechaUltIngreso;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "oferente")
	private Boolean oferente;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "logueado")
	private boolean logueado;

	@Column
	private String codigoValidacion;

	@Column
	private Date fechaCodigoValidacion;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_domicilio")
	private Domicilio domicilio;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_tipo_usuario")
	private TipoUsuario tipoUsuario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_usuario")
	private List<UsuarioRubro> usuarioRubros = new ArrayList<>();


	//CONSTRUCTOR
	public Usuario() {
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

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<UsuarioRubro> getUsuarioRubros() {
		return usuarioRubros;
	}

	public void setUsuarioRubros(List<UsuarioRubro> usuarioRubros) {
		this.usuarioRubros = usuarioRubros;
	}

	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public void setFechaCodigoValidacion(Date fechaCodigoValidacion) {
		this.fechaCodigoValidacion = fechaCodigoValidacion;
	}

	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	public Date getFechaCodigoValidacion() {
		return fechaCodigoValidacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void addRubro(UsuarioRubro usuarioRubro){
		usuarioRubros.add(usuarioRubro);
	}
	
	public void eliminarRubro(UsuarioRubro usuarioRubro){
		usuarioRubros.remove(usuarioRubro);
	}

	public boolean isLogueado() {
		return logueado;
	}

	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
	}
	
}
