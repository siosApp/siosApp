package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.Domicilio;
import com.utn.meraki.model.DomicilioModel;
import com.utn.meraki.repository.DomicilioRepository;

@Component("domicilioConverter")

public class DomicilioConverter {
	
	//REPOSITORY
	@Autowired
	DomicilioRepository domicilioRepository;
	
	//CONVERTER
	//Me crea o edita un domicilio
	public Domicilio convertDomicilioModelToDomicilio(DomicilioModel domicilioModel) {
		Domicilio domicilio = null;
		if(domicilioModel.getId()!=null) {
			domicilio = domicilioRepository.findDomicilioById(domicilioModel.getId());
		}else {
			domicilio = new Domicilio();
		}
		domicilio = new Domicilio();
		domicilio.setCalle(domicilioModel.getCalle());
		domicilio.setCodigoPostal(domicilioModel.getCodigoPostal());
		domicilio.setLatitud(domicilioModel.getLatitud());
		domicilio.setLongitud(domicilioModel.getLongitud());
		domicilio.setNumero(domicilioModel.getNumero());
		domicilio.setPiso(domicilioModel.getPiso());
		domicilioRepository.save(domicilio);
		return domicilio;
	}
	
	//Me muestra un domicilio en pantalla
	public DomicilioModel convertDomicilioToDomicilioModel(Domicilio domicilio) {
		DomicilioModel domicilioModel = new DomicilioModel();
		domicilioModel.setId(domicilio.getId());
		domicilioModel.setCalle(domicilio.getCalle());
		domicilioModel.setCodigoPostal(domicilio.getCodigoPostal());
		domicilioModel.setLatitud(domicilio.getLatitud());
		domicilioModel.setLongitud(domicilio.getLongitud());
		domicilioModel.setNumero(domicilio.getNumero());
		domicilioModel.setPiso(domicilio.getPiso());
		return domicilioModel;
	}

}
