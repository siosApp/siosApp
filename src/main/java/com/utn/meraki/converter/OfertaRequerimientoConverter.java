package com.utn.meraki.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.OfertaRequerimiento;
import com.utn.meraki.model.OfertaRequerimientoModel;
import com.utn.meraki.repository.RequerimientoRepository;
import com.utn.meraki.repository.UsuarioRepository;

@Component("ofertaRequerimientoConverter")

public class OfertaRequerimientoConverter {
	
	//REPOSITORY
	@Autowired
	RequerimientoRepository requerimientoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public OfertaRequerimiento convertOfertaRequerimientoModelToOfertaRequerimiento(OfertaRequerimientoModel ofertaRequerimientoModel) {
		OfertaRequerimiento ofertaRequerimiento = new OfertaRequerimiento();
		ofertaRequerimiento.setRequerimiento(requerimientoRepository.findRequerimientoById(ofertaRequerimientoModel.getIdRequerimiento()));
		ofertaRequerimiento.setUsuario(usuarioRepository.findUsuarioById(ofertaRequerimientoModel.getIdUsuario()));
		ofertaRequerimiento.setPrecioOfertado(ofertaRequerimientoModel.getPrecioOfertado());
		ofertaRequerimiento.setAsignado(false);
		ofertaRequerimiento.setRespuesta(ofertaRequerimientoModel.getRespuesta());
		ofertaRequerimiento.setFechaOferta(new Date(System.currentTimeMillis()));
		return ofertaRequerimiento;
	}
	
	public OfertaRequerimientoModel convertOfertaRequerimientoToOfertaRequerimientoModel(OfertaRequerimiento ofertaRequerimiento) {
		OfertaRequerimientoModel ofertaRequerimientoModel = new OfertaRequerimientoModel();
		ofertaRequerimientoModel.setId(ofertaRequerimiento.getId());
		ofertaRequerimientoModel.setIdRequerimiento(ofertaRequerimiento.getRequerimiento().getId());
		ofertaRequerimientoModel.setIdUsuario(ofertaRequerimiento.getUsuario().getId());
		ofertaRequerimientoModel.setAsignado(ofertaRequerimiento.getAsignado());
		ofertaRequerimientoModel.setPrecioOfertado(ofertaRequerimiento.getPrecioOfertado());
		ofertaRequerimientoModel.setRespuesta(ofertaRequerimiento.getRespuesta());
		ofertaRequerimientoModel.setFechaOferta(ofertaRequerimiento.getFechaOferta());
		return ofertaRequerimientoModel;
	}

}
