package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.EstadoRequerimientoConverter;
import com.utn.meraki.entity.EstadoRequerimiento;
import com.utn.meraki.model.EstadoRequerimientoModel;
import com.utn.meraki.repository.EstadoRequerimientoRepository;
import com.utn.meraki.service.EstadoRequerimientoService;

@Service("EstadoRequerimientoService")

public class EstadoRequerimientoServiceImpl implements EstadoRequerimientoService {
	
	@Autowired
	EstadoRequerimientoConverter estadoRequerimientoConverter;
	
	//REPOSITORY
	@Autowired
	EstadoRequerimientoRepository estadoRequerimientoRepository;
	
	//SERVICIOS
	@Override //crea un estado requerimiento
	public EstadoRequerimientoModel crearEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel) {
		EstadoRequerimiento estadoRequerimiento = estadoRequerimientoConverter.convertEstadoReqModelToEstadoReq(estadoRequerimientoModel);
		return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
	}
	


	@Override // Edita un estado requerimiento existente
	public EstadoRequerimientoModel editarEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel) {
		EstadoRequerimiento estadoRequerimiento = estadoRequerimientoConverter.convertEstadoReqModelToEstadoReq(estadoRequerimientoModel);
		return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
	}

	@Override // Me muestra todos los estados requerimientos vigentes
	public List<EstadoRequerimientoModel> listaEstadoRequerimientoVigente() {
		List<EstadoRequerimientoModel> listEstadoRequerimiento = new ArrayList<>();
		for(EstadoRequerimiento estadoRequerimiento : estadoRequerimientoRepository.findAll()) {
			if(estadoRequerimiento.getFechaBaja()==null) {
				listEstadoRequerimiento.add(estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento));
			}
		}
		return listEstadoRequerimiento;
	}

	@Override //Habilita un estado requerimiento que habia sido dado de baja
	public EstadoRequerimientoModel habilitarEstadoRequerimiento(String id) {
		EstadoRequerimiento estadoRequerimiento = estadoRequerimientoRepository.findEstadoRequerimientoById(id);
		estadoRequerimiento.setFechaBaja(null);
		estadoRequerimientoRepository.save(estadoRequerimiento);
		return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
	}

}

