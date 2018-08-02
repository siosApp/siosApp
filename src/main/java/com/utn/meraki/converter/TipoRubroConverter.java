package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.TipoRubro;
import com.utn.meraki.model.TipoRubroModel;
import com.utn.meraki.repository.TipoRubroRepository;

@Component("tipoRubroConverter")

public class TipoRubroConverter {
	
	//REPOSITORY
	@Autowired
	TipoRubroRepository tipoRubroRepository;
	
	//CONVERTER
	//Me crea un nuevo tipo de rubro
	public TipoRubro convertTipoRubroModelToTipoRubro(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro = new TipoRubro();
		tipoRubro.setNombreTipoRubro(tipoRubroModel.getNombreTipoRubro());
		tipoRubro.setDescripcion(tipoRubroModel.getDescripcion());
		tipoRubroRepository.save(tipoRubro);
		return tipoRubro;
	}
	
	//Me edita un tipo de rubro existente
	public TipoRubro convertTipoRubroModelToTipoRubroEditado(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro = tipoRubroRepository.findTipoRubroById(tipoRubroModel.getId());
		tipoRubro.setNombreTipoRubro(tipoRubroModel.getNombreTipoRubro());
		tipoRubro.setDescripcion(tipoRubroModel.getDescripcion());
		tipoRubroRepository.save(tipoRubro);
		return tipoRubro;
	}
	
	//Me muestra un tipo de rubro en pantalla
	public TipoRubroModel convertTipoRubroToTipoRubroModel(TipoRubro tipoRubro) {
		TipoRubroModel tipoRubroModel = new TipoRubroModel();
		tipoRubroModel.setId(tipoRubro.getId());
		tipoRubroModel.setNombreTipoRubro(tipoRubro.getNombreTipoRubro());
		tipoRubroModel.setDescripcion(tipoRubro.getDescripcion());
		tipoRubroModel.setFechaBaja(tipoRubro.getFechaBaja());
		return tipoRubroModel;
	}

}
