package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Provincia;
import com.utn.meraki.model.ProvinciaModel;
import com.utn.meraki.repository.ProvinciaRepository;

@Component("provinciaConverter")

public class ProvinciaConverter {
	
	//REPOSITORY
	@Autowired
	ProvinciaRepository provinciaRepository;
	
	//CONVERTERS
	//Me crea o edita una provincia
	public Provincia convertProvinciaModelToProvincia(ProvinciaModel provinciaModel) {
		Provincia provincia = null;
		if(provinciaModel.getId()!=null) {
			provincia = provinciaRepository.findProvinciaById(provinciaModel.getId());
		}else {
			provincia = new Provincia();
		}
		provincia.setNombreProvincia(provinciaModel.getNombreProvincia());
		provincia.setFechaBaja(provinciaModel.getFechaBaja());
		provinciaRepository.save(provincia);
		return provincia;
	}
	
	//Me muestra una provincia en pantalla
	public ProvinciaModel convertProvinciaToProvinciaModel(Provincia provincia) {
		ProvinciaModel provinciaModel = new ProvinciaModel();
		provinciaModel.setId(provincia.getId());
		provinciaModel.setNombreProvincia(provincia.getNombreProvincia());
		provinciaModel.setFechaBaja(provincia.getFechaBaja());
		return provinciaModel;
	}
	
}
