package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Rubro;
import com.utn.meraki.model.RubroModel;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.repository.TipoRubroRepository;

@Component("rubroConverter")

public class RubroConverter {
	
	//REPOSITORY
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	TipoRubroRepository tipoRubroRepository;
	
	public Rubro convertRubroModelToRubro(RubroModel rubroModel) {
		Rubro rubro = null;
		if(rubroModel.getId()!=null) {
			rubro = rubroRepository.findRubroById(rubroModel.getId());
		}else {
			rubro = new Rubro();
		}
		rubro.setNombreRubro(rubroModel.getNombreRubro());
		rubro.setTipoRubro(tipoRubroRepository.findTipoRubroByNombreTipoRubro(rubroModel.getNombreTipoRubro()));
		rubro.setDescripcion(rubroModel.getDescripcion());
		rubroRepository.save(rubro);
		return rubro;
	}
	
	public RubroModel convertRubroToRubroModel(Rubro rubro) {
		RubroModel rubroModel = new RubroModel();
		rubroModel.setId(rubro.getId());
		rubroModel.setDescripcion(rubro.getDescripcion());
		rubroModel.setFechaBaja(rubro.getFechaBaja());
		rubroModel.setNombreRubro(rubro.getNombreRubro());
		rubroModel.setNombreTipoRubro(rubro.getTipoRubro().getNombreTipoRubro());
		return rubroModel;
	}

}
