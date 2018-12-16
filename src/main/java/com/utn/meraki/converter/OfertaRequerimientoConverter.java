package com.utn.meraki.converter;

import java.util.Date;

import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.service.MailService;
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
	@Autowired
	MailService mailService;

	public OfertaRequerimiento convertOfertaRequerimientoModelToOfertaRequerimiento(OfertaRequerimientoModel ofertaRequerimientoModel) {
		OfertaRequerimiento ofertaRequerimiento = new OfertaRequerimiento();
		Requerimiento requerimiento = requerimientoRepository.findRequerimientoById(ofertaRequerimientoModel.getIdRequerimiento());
		ofertaRequerimiento.setRequerimiento(requerimiento);
		ofertaRequerimiento.setUsuario(usuarioRepository.findUsuarioById(ofertaRequerimientoModel.getIdUsuario()));
		ofertaRequerimiento.setPrecioOfertado(ofertaRequerimientoModel.getPrecioOfertado());
		ofertaRequerimiento.setAsignado(false);
		ofertaRequerimiento.setRespuesta(ofertaRequerimientoModel.getRespuesta());
		ofertaRequerimiento.setFechaOferta(new Date(System.currentTimeMillis()));
		if(requerimiento.getUsuario().getMail() != null) {
			enviarMail(requerimiento.getUsuario().getMail());
		}
		return ofertaRequerimiento;
	}

	private void enviarMail(String email){
		mailService.enviarMail(email,"Notificación sobre ofertas!","Te han realizado una oferta en tu requerimiento.");
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
