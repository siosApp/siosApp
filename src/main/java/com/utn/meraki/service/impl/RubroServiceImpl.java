package com.utn.meraki.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.RubroConverter;
import com.utn.meraki.entity.Rubro;
import com.utn.meraki.model.RubroModel;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.service.RubroService;

@Service("rubroService")

public class RubroServiceImpl implements RubroService{
	
	//CONVERTER
	@Autowired
	RubroConverter rubroConverter;
	
	//REPOSITORY
	@Autowired
	RubroRepository rubroRepository;

	//SERVICIOS
	@Override//Me crea un nuevo rubro
	public RubroModel crearRubro(RubroModel rubroModel) {
		Rubro rubro = rubroConverter.convertRubroModelToRubro(rubroModel);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override//Me edita un rubro ya existente
	public RubroModel editarRubro(RubroModel rubroModel) {
		Rubro rubro = rubroConverter.convertRubroModelToRubro(rubroModel);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override//Me muestra todos los rubros vigentes
	public List<RubroModel> listRubroVigente() {
		List<RubroModel> listRubro = new ArrayList<>();
		for(Rubro rubro : rubroRepository.findAll()) {
			if(rubro.getFechaBaja()==null) {
				listRubro.add(rubroConverter.convertRubroToRubroModel(rubro));
			}
		}
		return listRubro;
	}

	@Override//Me da de baja un rubro
	public RubroModel bajaRubro(String id) {
		Rubro rubro = rubroRepository.findRubroById(id);
		rubro.setFechaBaja(new Date(System.currentTimeMillis()));
		rubroRepository.save(rubro);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

}
