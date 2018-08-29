package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.LocalidadConverter;
import com.utn.meraki.entity.Localidad;
import com.utn.meraki.model.LocalidadModel;
import com.utn.meraki.repository.LocalidadRepository;
import com.utn.meraki.service.LocalidadService;

@Service("localidadService")

public class LocalidadServiceImpl implements LocalidadService{

	//CONVERTER
	@Autowired
	LocalidadConverter localidadConverter;
	
	//REPOSITORY
	@Autowired
	LocalidadRepository localidadRepository;
	
	//SERVICIOS
	@Override //Me crea una nueva Localidad
	public LocalidadModel crearLocalidad(LocalidadModel localidadModel) {
		Localidad localidad = localidadConverter.convertLocalidadModelToLocalidad(localidadModel);
		return localidadConverter.convertLocalidadToLocalidadModel(localidad);
	}

	@Override //Me edita una Localidad existente
	public LocalidadModel editarLocalidad(LocalidadModel localidadModel) {
		Localidad localidad = localidadConverter.convertLocalidadModelToLocalidad(localidadModel);
		return localidadConverter.convertLocalidadToLocalidadModel(localidad);
	}

	@Override //Me habilita una Localidad que había sido dado de baja
	public LocalidadModel habilitarLocalidad(String id) {
		Localidad localidad = localidadRepository.findLocalidadById(id);
		localidad.setFechaBaja(null);
		localidadRepository.save(localidad);
		return localidadConverter.convertLocalidadToLocalidadModel(localidad);
	}

	@Override //Me muestra todas las Localidades vigentes en pantalla
	public List<LocalidadModel> listLocalidadVigente() {
		List<LocalidadModel> listLocalidad = new ArrayList<>();
		for(Localidad localidad : localidadRepository.findAll()) {
			if(localidad.getFechaBaja()==null) {
				listLocalidad.add(localidadConverter.convertLocalidadToLocalidadModel(localidad));
			}
		}
		return listLocalidad;
	}

	@Override
	public List<LocalidadModel> listLocalidadTodos() {
		List<LocalidadModel> listLocalidad = new ArrayList<>();
		for(Localidad localidad : localidadRepository.findAll()) {
			listLocalidad.add(localidadConverter.convertLocalidadToLocalidadModel(localidad));
		}
		return listLocalidad;
	}

	@Override
	public LocalidadModel getLocalidadById(String id) {
		if(id !=null){
			return localidadConverter.convertLocalidadToLocalidadModel(localidadRepository.findLocalidadById(id));
		}
		return new LocalidadModel();
	}

	@Override
	public LocalidadModel deshabilitarLocalidad(String id) {
		Localidad localidad = localidadRepository.findLocalidadById(id);
		localidad.setFechaBaja(new java.util.Date());
		localidadRepository.save(localidad);
		return localidadConverter.convertLocalidadToLocalidadModel(localidad);
	}

}