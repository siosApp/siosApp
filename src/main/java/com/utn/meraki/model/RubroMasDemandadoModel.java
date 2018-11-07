package com.utn.meraki.model;

public class RubroMasDemandadoModel implements Comparable<RubroMasDemandadoModel>{
	
	//ATRIBUTOS
	private String nombreRubro;
	
	private Integer cantidadSolicitudes;
	
	//CONSTRUCTORES
	public RubroMasDemandadoModel() {
		
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public Integer getCantidadSolicitudes() {
		return cantidadSolicitudes;
	}

	public void setCantidadSolicitudes(Integer cantidadSolicitudes) {
		this.cantidadSolicitudes = cantidadSolicitudes;
	}

	@Override
	public int compareTo(RubroMasDemandadoModel o) {
		 String a=new String(String.valueOf(this.getCantidadSolicitudes())+this.getNombreRubro());
	     String b=new String(String.valueOf(o.getCantidadSolicitudes())+o.getNombreRubro());
	     return b.compareTo(a);
	}
	
	
	
}
