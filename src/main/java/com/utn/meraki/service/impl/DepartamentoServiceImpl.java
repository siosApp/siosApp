package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.meraki.entity.Provincia;
import com.utn.meraki.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.DepartamentoConverter;
import com.utn.meraki.entity.Departamento;
import com.utn.meraki.model.DepartamentoModel;
import com.utn.meraki.repository.DepartamentoRepository;
import com.utn.meraki.service.DepartamentoService;

@Service("departamentoService")

public class DepartamentoServiceImpl implements DepartamentoService{

	//CONVERTER
	@Autowired
	DepartamentoConverter departamentoConverter;
	
	//REPOSITORY
	@Autowired
	DepartamentoRepository departamentoRepository;
	@Autowired
	ProvinciaRepository provinciaRepository;
	
	//SERVICIOS
	@Override //Me crea un nuevo departamento
	public DepartamentoModel crearDepartamento(DepartamentoModel departamentoModel) {
		Departamento departamento = departamentoConverter.convertDepartamentoModelToDepartamento(departamentoModel);
		return departamentoConverter.convertDepartamentoToDepartamentoModel(departamento);
	}

	@Override //Me edita un departamento existente
	public DepartamentoModel editarDepartamento(DepartamentoModel departamentoModel) {
		Departamento departamento = departamentoConverter.convertDepartamentoModelToDepartamento(departamentoModel);
		return departamentoConverter.convertDepartamentoToDepartamentoModel(departamento);
	}

	@Override //Me habilita un departamento que había sido dado de baja
	public DepartamentoModel habilitarDepartamento(String id) {
		Departamento departamento = departamentoRepository.findDepartamentoById(id);
		departamento.setFechaBaja(null);
		departamentoRepository.save(departamento);
		return departamentoConverter.convertDepartamentoToDepartamentoModel(departamento);
	}

	@Override //Me muestra todos los departamento vigentes en pantalla
	public List<DepartamentoModel> listDepartamentoVigente() {
		List<DepartamentoModel> listDepartamentos = new ArrayList<>();
		for(Departamento departamento : departamentoRepository.findAll()) {
			if(departamento.getFechaBaja()==null) {
				listDepartamentos.add(departamentoConverter.convertDepartamentoToDepartamentoModel(departamento));
			}
		}
		return listDepartamentos;
	}

	@Override
	public List<DepartamentoModel> listDepartamentoVigenteByProvincia(String provincia) {
		//Obtengo provincia por medio del id.
		Provincia prov = provinciaRepository.findProvinciaById(provincia);
		List<DepartamentoModel> listDepartamentos = new ArrayList<>();
		for(Departamento departamento : departamentoRepository.findDepartamentoByProvincia(prov)) {
			if(departamento.getFechaBaja()==null) {
				listDepartamentos.add(departamentoConverter.convertDepartamentoToDepartamentoModel(departamento));
			}
		}
		return listDepartamentos;
	}

	@Override
	public List<DepartamentoModel> listDepartamentoTodos() {
		List<DepartamentoModel> listDepartamentos = new ArrayList<>();
		for(Departamento departamento : departamentoRepository.findAll()) {
			listDepartamentos.add(departamentoConverter.convertDepartamentoToDepartamentoModel(departamento));
		}
		return listDepartamentos;
	}

	@Override
	public DepartamentoModel getDepartamentoById(String id) {
		if(id !=null){
			return departamentoConverter.convertDepartamentoToDepartamentoModel(departamentoRepository.findDepartamentoById(id));
		}
		return new DepartamentoModel();
	}

	@Override
	public DepartamentoModel getDepartamentoByNombre(String nombreDepartamento) {
		return departamentoConverter.convertDepartamentoToDepartamentoModel(departamentoRepository.findDepartamentoByNombreDepartamento(nombreDepartamento));
	}

	@Override
	public DepartamentoModel getDepartamentoByNombreAndProvincia(String depto, String nombreProvincia) {
		Provincia provincia=provinciaRepository.findProvinciaByNombreProvincia(nombreProvincia);
		return  departamentoConverter.convertDepartamentoToDepartamentoModel(departamentoRepository.findDepartamentoByNombreDepartamentoAndProvincia(depto,provincia));

	}

	@Override
	public DepartamentoModel deshabilitarDepartamento(String id) {
		Departamento departamento = departamentoRepository.findDepartamentoById(id);
		departamento.setFechaBaja(new java.util.Date());
		departamentoRepository.save(departamento);
		return departamentoConverter.convertDepartamentoToDepartamentoModel(departamento);
	}

}
