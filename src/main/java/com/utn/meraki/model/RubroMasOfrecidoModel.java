package com.utn.meraki.model;

public class RubroMasOfrecidoModel implements Comparable<RubroMasOfrecidoModel>{
	
	//ATRIBUTOS
	private String nombreRubro;
		
	private Integer cantidadSolicitudes;
	
	//CONSTRUCTORES
	public RubroMasOfrecidoModel() {
		
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

	//MÉTODO DE ORDENAMIENTO
	@Override
	public int compareTo(RubroMasOfrecidoModel o) {
		 String a=new String(String.valueOf(this.getCantidadSolicitudes())+this.getNombreRubro());
	     String b=new String(String.valueOf(o.getCantidadSolicitudes())+o.getNombreRubro());
	     return b.compareTo(a);
	}
	
}
