package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.meraki.entity.Departamento;
import com.utn.meraki.entity.Domicilio;
import com.utn.meraki.entity.Provincia;
import com.utn.meraki.repository.DepartamentoRepository;
import com.utn.meraki.repository.DomicilioRepository;
import com.utn.meraki.repository.ProvinciaRepository;
import org.apache.tomcat.jni.Local;
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

	@Autowired
	DepartamentoRepository departamentoRepository;
	@Autowired
	ProvinciaRepository provinciaRepository;
	@Autowired
	DomicilioRepository domicilioRepository;

	//SERVICIOS
	@Override //Me crea una nueva Localidad
	public LocalidadModel crearLocalidad(LocalidadModel localidadModel) {
		Departamento departamento= departamentoRepository.findDepartamentoById(localidadModel.getDepartamento().getId());
		if(departamento!=null){
			Localidad localidad=localidadConverter.convertLocalidadModelToLocalidad(localidadModel);
			localidadRepository.save(localidad);
			return localidadConverter.convertLocalidadToLocalidadModel(localidad);
		}
		return new LocalidadModel();
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
	public List<LocalidadModel> listLocalidadesVigenteByDepartamento(String departamento) {
		//Obtengo departamento
		Departamento depto= departamentoRepository.findDepartamentoById(departamento);
		List<LocalidadModel> listLocalidad = new ArrayList<>();
		for(Localidad localidad : localidadRepository.findLocalidadByDepartamento(depto)) {
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
	public LocalidadModel getLocalidadByDomicilio(String id) {
		Domicilio domicilio= domicilioRepository.findDomicilioById(id);
		if(domicilio !=null){
			return localidadConverter.convertLocalidadToLocalidadModel(domicilio.getLocalidad());
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

	@Override
	public List<LocalidadModel> findLocalidadesByProvinciaAndDepartamento(String nombreProvincia, String nombreDepartamento) {
		List<LocalidadModel> localidadModels= new ArrayList<>();
		Provincia provincia=provinciaRepository.findProvinciaByNombreProvincia(nombreProvincia);
		Departamento departamento=departamentoRepository.findDepartamentoByNombreDepartamentoAndProvincia(nombreDepartamento,provincia);
		for(Localidad localidad:localidadRepository.findLocalidadByDepartamento(departamento)){
			if(localidad.getFechaBaja()==null){
				localidadModels.add(localidadConverter.convertLocalidadToLocalidadModel(localidad));
			}
		}
		return localidadModels;
	}

	@Override
	public LocalidadModel findLocalidadesByNombreLocalidadProvinciaAndDepartamento(String nombreLocalidad, String nombreProvincia, String nombreDepartamento) {
		Provincia provincia=provinciaRepository.findProvinciaByNombreProvincia(nombreProvincia);
		Departamento departamento=departamentoRepository.findDepartamentoByNombreDepartamentoAndProvincia(nombreDepartamento,provincia);
		return localidadConverter.convertLocalidadToLocalidadModel(localidadRepository.findLocalidadByNombreLocalidadAndDepartamento(nombreLocalidad,departamento));
	}

}