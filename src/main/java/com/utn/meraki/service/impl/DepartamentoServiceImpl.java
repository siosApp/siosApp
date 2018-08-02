package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;
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

}
