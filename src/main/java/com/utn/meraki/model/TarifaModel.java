package com.utn.meraki.model;

import java.sql.Date;

public class TarifaModel {
	
	//ATRIBUTOS
	private String id;
	
	private Integer montoTarifa;
	
	private Date fechaBaja;
	
	//CONSTRUCTOR
	public TarifaModel() {
		
	}

	//GET AND SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMontoTarifa() {
		return montoTarifa;
	}

	public void setMontoTarifa(Integer montoTarifa) {
		this.montoTarifa = montoTarifa;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
}
