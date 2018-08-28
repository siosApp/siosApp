package com.utn.meraki.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.MedioPagoConverter;
import com.utn.meraki.entity.MedioPago;
import com.utn.meraki.model.MedioPagoModel;
import com.utn.meraki.repository.MedioPagoRepository;
import com.utn.meraki.service.MedioPagoService;

@Service("medioPagoService")

public class MedioPagoServiceImpl implements MedioPagoService{

	//CONVERTER
	@Autowired
	MedioPagoConverter medioPagoConverter;
	
	//REPOSITORY
	@Autowired
	MedioPagoRepository medioPagoRepository;
	
	//SERVICIOS
	@Override //Me crea un nuevo medio de pago
	public MedioPagoModel crearMedioPago(MedioPagoModel medioPagoModel) {
		MedioPago medioPago = medioPagoConverter.convertMedioPagoModelToMedioPago(medioPagoModel);
		return medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPago);
	}

	@Override //Me edita un medio de pago ya existente
	public MedioPagoModel editarMedioPago(MedioPagoModel medioPagoModel) {
		MedioPago medioPago = medioPagoConverter.convertMedioPagoModelToMedioPago(medioPagoModel);
		return medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPago);
	}

	@Override //Me muestra todas los medios de pagos
	public List<MedioPagoModel> listMedioPagoVigente() {
		List<MedioPagoModel> listMedioPagos = new ArrayList<>();
		for(MedioPago medioPago : medioPagoRepository.findAll()) {
			if(medioPago.getFechaBaja()==null) {
				listMedioPagos.add(medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPago));
			}
		}
		return listMedioPagos;
	}

	@Override //Me habilita un medio de pago que habia sido dado de baja
	public MedioPagoModel habilitarMedioPago(String id) {
		MedioPago medioPago = medioPagoRepository.findMedioPagoById(id);
		medioPago.setFechaBaja(null);
		medioPagoRepository.save(medioPago);
		return medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPago);
	}
	
	//Me deshabilita un medio de pago que habia sido dado de alta
	@Override
    public MedioPagoModel deshabilitarMedioPago(String id) {
		MedioPago medioPago = medioPagoRepository.findMedioPagoById(id);
		medioPago.setFechaBaja(new Date());
		medioPagoRepository.save(medioPago);
        return medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPago);
    }
	
	//busqueda de medio de pago por id
	 @Override
	    public MedioPagoModel getMedioPagoById(String id) {
	        if(id !=null){
	            return medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPagoRepository.findMedioPagoById(id));
	        }
	        return new MedioPagoModel();
	    }
	 
	 @Override
	    public List<MedioPagoModel> listMedioPagoTodas() {
	        List<MedioPagoModel> listMedioPago = new ArrayList<>();
	        for(MedioPago medioPago : medioPagoRepository.findAll()) {
	        	listMedioPago.add(medioPagoConverter.convertMedioPagoToMedioPagoModel(medioPago));
	        }
	        return listMedioPago;
	    }

}