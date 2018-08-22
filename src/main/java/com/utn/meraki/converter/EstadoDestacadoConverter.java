package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.EstadoDestacado;
import com.utn.meraki.model.EstadoDestacadoModel;
import com.utn.meraki.repository.EstadoDestacadoRepository;

@Component("estadoDestacadoConverter")

public class EstadoDestacadoConverter {
	
	//REPOSITORY
	@Autowired
	EstadoDestacadoRepository estadoDestacadoRepository;
	
	//CONVERTERS
	//Me crea o edita un estado de destacado
	public EstadoDestacado convertEstadoDestacadoModelToEstadoDestacado(EstadoDestacadoModel estadoDestacadoModel) {
		EstadoDestacado estadoDestacado = null;
		if(estadoDestacadoModel.getId()!=null) {
			estadoDestacado = estadoDestacadoRepository.findEstadoDestacadoById(estadoDestacadoModel.getId());
		}else {
			estadoDestacado = new EstadoDestacado();
		}
		estadoDestacado.setNombreEstadoDestacado(estadoDestacadoModel.getNombreEstadoDestacado());
		estadoDestacado.setFechaBaja(estadoDestacadoModel.getFechaBaja());
		estadoDestacadoRepository.save(estadoDestacado);
		return estadoDestacado;
	}
	
	//Me muestra un medio de pago en pantalla
	public EstadoDestacadoModel convertEstadoDestacadoToEstadoDestacadoModel(EstadoDestacado estadoDestacado) {
		EstadoDestacadoModel estadoDestacadoModel = new EstadoDestacadoModel();
		estadoDestacadoModel.setId(estadoDestacado.getId());
		estadoDestacadoModel.setNombreEstadoDestacado(estadoDestacado.getNombreEstadoDestacado());
		estadoDestacadoModel.setFechaBaja(estadoDestacado.getFechaBaja());
		return estadoDestacadoModel;
	}

}

