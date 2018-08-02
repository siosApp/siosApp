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
	
	//CONVERTER
	//Me crea una nueva localidad
	public Localidad convertLocalidadModelToLocalidad(LocalidadModel localidadModel) {
		Localidad localidad = new Localidad();
		localidad.setNombreLocalidad(localidadModel.getNombreLocalidad());
		localidad.setDepartamento(departamentoRepository.findDepartamentoByNombreDepartamento(localidadModel.getNombreDepartamento()));
		return localidad;
	}
	
	//Me edita una localidad existente
	public Localidad convertLocalidadModelToLocalidadEditada(LocalidadModel localidadModel) {
		Localidad localidad = localidadRepository.findLocalidadById(localidadModel.getId());
		localidad.setNombreLocalidad(localidadModel.getNombreLocalidad());
		localidad.setDepartamento(departamentoRepository.findDepartamentoByNombreDepartamento(localidadModel.getNombreDepartamento()));
		localidad.setFechaBaja(localidadModel.getFechaBaja());
		return localidad;
	}
	
	//Me muestra una localidad en pantalla
	public LocalidadModel convertLocalidadToLocalidadModel(Localidad localidad) {
		LocalidadModel localidadModel = new LocalidadModel();
		localidadModel.setNombreDepartamento(localidad.getDepartamento().getNombreDepartamento());
		localidadModel.setNombreLocalidad(localidad.getNombreLocalidad());
		localidadModel.setFechaBaja(localidad.getFechaBaja());
		localidadModel.setId(localidad.getId());
		return localidadModel;
	}

}
