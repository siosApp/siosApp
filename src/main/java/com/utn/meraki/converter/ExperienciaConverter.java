package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.Experiencia;
import com.utn.meraki.model.ExperienciaModel;
import com.utn.meraki.repository.ExperienciaRepository;

@Component("experienciaConverter")

public class ExperienciaConverter {
	
	//REPOSITORY
	@Autowired
	ExperienciaRepository experienciaRepository;
	
	//CONVERTERS
	//Me crea o edita una Experiencia
	public Experiencia convertExperienciaModelToExperiencia(ExperienciaModel experienciaModel) {
		Experiencia experiencia = null;
		if(experienciaModel.getId()!=null) {
			experiencia = experienciaRepository.findExperienciaById(experienciaModel.getId());
		}else {
			experiencia = new Experiencia();
		}
		experiencia.setDescripcion(experienciaModel.getDescripcion());
		experiencia.setFechaDesde(experienciaModel.getFechaDesde());
		experiencia.setFechaHasta(experienciaModel.getFechaHasta());
		experienciaRepository.save(experiencia);
		return experiencia;
	}
	
	//Me muestra en pantalla una experiencia
	public ExperienciaModel convertExperienciaToExperienciaModel(Experiencia experiencia) {
		ExperienciaModel experienciaModel = new ExperienciaModel();
		experienciaModel.setId(experiencia.getId());
		experienciaModel.setDescripcion(experiencia.getDescripcion());
		experienciaModel.setFechaDesde(experiencia.getFechaDesde());
		experienciaModel.setFechaHasta(experiencia.getFechaHasta());
		return experienciaModel;
	}

}
