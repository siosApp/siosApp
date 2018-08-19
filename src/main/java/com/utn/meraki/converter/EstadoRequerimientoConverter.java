package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.EstadoRequerimiento;
import com.utn.meraki.model.EstadoRequerimientoModel;
import com.utn.meraki.repository.EstadoRequerimientoRepository;

@Component("estadoRequerimientoConverter")

public class EstadoRequerimientoConverter {
	
	//REPOSITORY
	@Autowired
	EstadoRequerimientoRepository estadoRequerimientoRepository;
	
	//CONVERTERS
	//Crea o edita el estado requerimiento
	public EstadoRequerimiento convertEstadoReqModelToEstadoReq(EstadoRequerimientoModel estadoRequerimientoModel) {
		EstadoRequerimiento estadoRequerimiento = null;
		if(estadoRequerimientoRepository.findEstadoRequerimientoById(estadoRequerimientoModel.getId()) != null) {
			estadoRequerimiento = estadoRequerimientoRepository.findEstadoRequerimientoById(estadoRequerimientoModel.getId());
		}else {
			estadoRequerimiento = new EstadoRequerimiento();
		}
		estadoRequerimiento.setNombreEstado(estadoRequerimientoModel.getNombreEstado());
		estadoRequerimiento.setId(estadoRequerimientoModel.getId());
		estadoRequerimiento.setFechaBaja(estadoRequerimientoModel.getFechaBaja());
		estadoRequerimientoRepository.save(estadoRequerimiento);
		return estadoRequerimiento;
	}
	
	//Muestra un estado requerimiento en pantalla
	public EstadoRequerimientoModel convertEstadoReqToEstadoReqModel(EstadoRequerimiento estadoRequerimiento) {
		EstadoRequerimientoModel estadoRequerimientoModel = new EstadoRequerimientoModel();
		estadoRequerimientoModel.setId(estadoRequerimiento.getId());
		estadoRequerimientoModel.setFechaBaja(estadoRequerimiento.getFechaBaja());
		estadoRequerimientoModel.setNombreEstado(estadoRequerimiento.getNombreEstado());
		return estadoRequerimientoModel;
	}
}
