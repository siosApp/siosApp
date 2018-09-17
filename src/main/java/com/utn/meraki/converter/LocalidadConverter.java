package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.Localidad;
import com.utn.meraki.model.LocalidadModel;
import com.utn.meraki.repository.DepartamentoRepository;
import com.utn.meraki.repository.LocalidadRepository;

@Component("localidadConverter")

public class LocalidadConverter {
	
	//REPOSITORY
	@Autowired
	LocalidadRepository localidadRepository;
	@Autowired
	DepartamentoRepository departamentoRepository;
	@Autowired
	DepartamentoConverter departamentoConverted;
	
	//CONVERTER
	public Localidad convertLocalidadModelToLocalidad(LocalidadModel localidadModel) {
		Localidad localidad = null;
		if(localidadModel.getId()!= null) {
			localidad = localidadRepository.findLocalidadById(localidadModel.getId());
		}else {
			localidad = new Localidad();
		}
		localidad.setNombreLocalidad(localidadModel.getNombreLocalidad());
		localidad.setDepartamento(departamentoRepository.findDepartamentoById(localidadModel.getDepartamento().getId()));
		localidadRepository.save(localidad);
		return localidad;
	}
	
	public LocalidadModel convertLocalidadToLocalidadModel(Localidad localidad) {
		LocalidadModel localidadModel = new LocalidadModel();
		localidadModel.setId(localidad.getId());
		localidadModel.setFechaBaja(localidad.getFechaBaja());
		localidadModel.setNombreLocalidad(localidad.getNombreLocalidad());
		localidadModel.setDepartamento(departamentoConverted.convertDepartamentoToDepartamentoModel(localidad.getDepartamento()));
		return localidadModel;
	}

}
