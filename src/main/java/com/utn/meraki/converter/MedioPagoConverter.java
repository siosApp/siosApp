package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.MedioPago;
import com.utn.meraki.model.MedioPagoModel;
import com.utn.meraki.repository.MedioPagoRepository;

@Component("medioPagoConverter")

public class MedioPagoConverter {
	
	//REPOSITORY
	@Autowired
	MedioPagoRepository medioPagoRepository;
	
	//CONVERTERS
	//Me crea o edita un medio de pago
	public MedioPago convertMedioPagoModelToMedioPago(MedioPagoModel medioPagoModel) {
		MedioPago medioPago = null;
		if(medioPagoModel.getId()!=null) {
			medioPago = medioPagoRepository.findMedioPagoById(medioPagoModel.getId());
		}else {
			medioPago = new MedioPago();
		}
		medioPago.setNombreMedioPago(medioPagoModel.getNombreMedioPago());
		medioPago.setFechaBaja(medioPagoModel.getFechaBaja());
		medioPagoRepository.save(medioPago);
		return medioPago;
	}
	
	//Me muestra un medio de pago en pantalla
	public MedioPagoModel convertMedioPagoToMedioPagoModel(MedioPago medioPago) {
		MedioPagoModel medioPagoModel = new MedioPagoModel();
		medioPagoModel.setId(medioPago.getId());
		medioPagoModel.setNombreMedioPago(medioPago.getNombreMedioPago());
		medioPagoModel.setFechaBaja(medioPago.getFechaBaja());
		return medioPagoModel;
	}

}

