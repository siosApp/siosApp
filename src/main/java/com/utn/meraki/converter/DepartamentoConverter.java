package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.Departamento;
import com.utn.meraki.model.DepartamentoModel;
import com.utn.meraki.repository.DepartamentoRepository;
import com.utn.meraki.repository.ProvinciaRepository;

@Component("departamentoConverter")

public class DepartamentoConverter {
	
	//REPOSITORY
	@Autowired
	ProvinciaRepository provinciaRepository;
	@Autowired
	DepartamentoRepository departamentoRepository;
	@Autowired
	ProvinciaConverter provinciaConverter;
	
	public Departamento convertDepartamentoModelToDepartamento(DepartamentoModel departamentoModel) {
		Departamento departamento = null;
		if(departamentoModel.getId()!= null) {
			departamento = departamentoRepository.findDepartamentoById(departamentoModel.getId());
		}else {
			departamento = new Departamento();
		}
		departamento.setNombreDepartamento(departamentoModel.getNombreDepartamento());
		departamento.setProvincia(provinciaRepository.findProvinciaByNombreProvincia(departamentoModel.getNombreProvincia()));
		departamentoRepository.save(departamento);
		return departamento;
	}
	
	public DepartamentoModel convertDepartamentoToDepartamentoModel(Departamento departamento) {
		DepartamentoModel departamentoModel = new DepartamentoModel();
		departamentoModel.setId(departamento.getId());
		departamentoModel.setFechaBaja(departamento.getFechaBaja());
		departamentoModel.setNombreDepartamento(departamento.getNombreDepartamento());
		departamentoModel.setNombreProvincia(departamento.getProvincia().getNombreProvincia());
		return departamentoModel;
	}

}
