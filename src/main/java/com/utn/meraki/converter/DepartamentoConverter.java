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
	
	//CONVERTER
	//Me crea un nuevo departamento
	public Departamento convertDepartamentoModelToDepartamento(DepartamentoModel departamentoModel) {
		Departamento departamento = new Departamento();
		departamento.setNombreDepartamento(departamentoModel.getNombreDepartamento());
		departamento.setProvincia(provinciaRepository.findProvinciaByNombreProvincia(departamentoModel.getNombreDepartamento()));
		departamentoRepository.save(departamento);
		return departamento;
	}
	
	//Me edita un nuevo departamento
	public Departamento convertDepartamentoModelToDepartamentoEditado(DepartamentoModel departamentoModel) {
		Departamento departamento = new Departamento();
		departamento.setNombreDepartamento(departamentoModel.getNombreDepartamento());
		departamento.setProvincia(provinciaRepository.findProvinciaByNombreProvincia(departamentoModel.getNombreDepartamento()));
		departamento.setFechaBaja(departamentoModel.getFechaBaja());
		departamentoRepository.save(departamento);
		return departamento;
	}
	
	//Me muestra un departamento en pantalla
	public DepartamentoModel convertDepartamentoToDepartamentoModel(Departamento departamento) {
		DepartamentoModel departamentoModel = new DepartamentoModel();
		departamentoModel.setId(departamento.getId());
		departamentoModel.setNombreDepartamento(departamento.getNombreDepartamento());
		departamentoModel.setFechaBaja(departamento.getFechaBaja());
		departamentoModel.setNombreProvincia(departamento.getProvincia().getNombreProvincia());
		return departamentoModel;
	}

}
