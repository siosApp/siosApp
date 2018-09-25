package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.OfertaRequerimientoModel;
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.model.SolicitudModel;

public interface RequerimientoService {
	
	public RequerimientoModel publicarRequerimiento(RequerimientoModel requerimientoModel);
	
	public OfertaRequerimientoModel ofertarRequerimiento(OfertaRequerimientoModel ofertaRequerimientoModel);
	
	public List<RequerimientoModel> getRequerimientosActivos();
	
	public RequerimientoModel findRequerimientoById(String idRequerimiento);
	
	public SolicitudModel aceptarOfertaRequerimiento(String idOfertaRequerimiento);

}