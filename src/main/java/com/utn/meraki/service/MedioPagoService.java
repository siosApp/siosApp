package com.utn.meraki.service;


import java.util.List;

import com.utn.meraki.model.MedioPagoModel;
//Medio de Pago
public interface MedioPagoService {
	
	public abstract MedioPagoModel crearMedioPago(MedioPagoModel medioPagoModel);
	
	public abstract MedioPagoModel editarMedioPago(MedioPagoModel medioPagoModel);
	
	public abstract List<MedioPagoModel> listMedioPagoVigente();
	
	public abstract MedioPagoModel habilitarMedioPago(String id);
	
	public abstract MedioPagoModel deshabilitarMedioPago(String id);
	
	public abstract MedioPagoModel getMedioPagoById(String id);
	
	public abstract List<MedioPagoModel> listMedioPagoTodas();

}