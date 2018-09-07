package com.utn.meraki.model;

import java.util.Date;

public class RequerimientoModel {
	
	
	//ATRIBUTOS
		private String id;
		
		private String descripcion;
		
		private Date fechaPublicacion;
		
		private Float precioAPagar;
		
		private Integer tiempoEstimado;
		
		private String nombreEstadoRequerimiento;
		
		private String nombreUsuario;
		
		
		
		//CONSTRUCTRO
		public RequerimientoModel() {
		}


		//SET A GET
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



		public Float getPrecioAPagar() {
			return precioAPagar;
		}



		public void setPrecioAPagar(Float precioAPagar) {
			this.precioAPagar = precioAPagar;
		}



		public Integer getTiempoEstimado() {
			return tiempoEstimado;
		}



		public void setTiempoEstimado(Integer tiempoEstimado) {
			this.tiempoEstimado = tiempoEstimado;
		}



		public String getNombreEstadoRequerimiento() {
			return nombreEstadoRequerimiento;
		}



		public void setNombreEstadoRequerimiento(String nombreEstadoRequerimiento) {
			this.nombreEstadoRequerimiento = nombreEstadoRequerimiento;
		}



		public String getNombreUsuario() {
			return nombreUsuario;
		}



		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}

		
	
	

}
