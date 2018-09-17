package com.utn.meraki.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.RequerimientoConverter;
import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.repository.RequerimientoRepository;
import com.utn.meraki.service.RequerimientoService;

@Service("requerimientoService")

public class RequerimientoServiceImpl implements RequerimientoService{

	//REPOSITORY
	@Autowired
	RequerimientoRepository requerimientoRepository;
	
	//CONVERTER
	@Autowired
	RequerimientoConverter requerimientoConverter;
	
	//METODOS
	@Override
	public RequerimientoModel publicarRequerimiento(RequerimientoModel requerimientoModel) {
		Requerimiento requerimiento = requerimientoConverter.convertRequerimientoModelToRequerimiento(requerimientoModel);
		requerimientoRepository.save(requerimiento);
		return requerimientoModel;
	}

}
