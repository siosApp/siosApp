package com.utn.meraki.model;

public class DatoPagoMercadoPagoModel {
	
	//ATRIBUTOS
	private String title; //nombre del producto o motivo del pago
	
	private int quantity; //cantidad de productos o pagos
	
	private String currency_id; //moneda (ARG - EUR - DOL)
	
	private double unit_price; //monto destacado
	
	private String idUsuario;
	
	private String idMedioPago;
	
	//CONSTRUCTOR
	public DatoPagoMercadoPagoModel() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(String idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	
	
}
