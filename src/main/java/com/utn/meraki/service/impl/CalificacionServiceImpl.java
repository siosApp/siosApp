package com.utn.meraki.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.CalificacionConverter;
import com.utn.meraki.entity.Calificacion;
import com.utn.meraki.model.CalificacionModel;
import com.utn.meraki.repository.CalificacionRepository;
import com.utn.meraki.service.CalificacionService;

@Service("calificacionService")

public class CalificacionServiceImpl implements CalificacionService{

	//REPOSITORY
	@Autowired
	CalificacionRepository calificacionRepository;
	
	//CONVERTER
	@Autowired
	CalificacionConverter calificacionConverter;
	
	@Override
	public CalificacionModel realizarCalificacion(CalificacionModel calificacionModel) {
		Calificacion calificacion = calificacionConverter.convertCalificacionModelToCalificacion(calificacionModel);
		calificacionRepository.save(calificacion);
		return calificacionConverter.convertCalificacionToCalificacionModel(calificacion);
	}

}
