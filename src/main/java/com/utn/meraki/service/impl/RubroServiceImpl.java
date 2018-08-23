package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.utn.meraki.converter.RubroConverter;
import com.utn.meraki.repository.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.entity.Rubro;
import com.utn.meraki.model.RubroModel;
import com.utn.meraki.service.RubroService;

@Service("rubroService")

public class RubroServiceImpl implements RubroService{

	@Autowired
	private RubroConverter rubroConverter;
	@Autowired
	private RubroRepository rubroRepository;

	@Override
	public RubroModel crearRubro(RubroModel rubroModel) {
		Rubro Rubro = rubroConverter.convertRubroModelToRubro(rubroModel);
		return rubroConverter.convertRubroToRubroModel(Rubro);
	}

	@Override
	public RubroModel editarRubro(RubroModel rubroModel) {
		Rubro rubro = rubroConverter.convertRubroModelToRubro(rubroModel);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override
	public List<RubroModel> listRubroVigente() {
		List<RubroModel> listRubros = new ArrayList<>();
		for(Rubro rubro : rubroRepository.findAll()) {
			if(rubro.getFechaBaja()==null) {
				listRubros.add(rubroConverter.convertRubroToRubroModel(rubro));
			}
		}
		return listRubros;
	}

	@Override
	public RubroModel habilitarRubro(String id) {
		Rubro rubro = rubroRepository.findRubroById(id);
		rubro.setFechaBaja(null);
		rubroRepository.save(rubro);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override
	public List<RubroModel> listRubroTodas() {
		List<RubroModel> listRubros = new ArrayList<>();
		for(Rubro rubro : rubroRepository.findAll()) {
			listRubros.add(rubroConverter.convertRubroToRubroModel(rubro));
		}
		return listRubros;
	}

	@Override
	public RubroModel getRubroById(String id) {
		if(id !=null){
			return rubroConverter.convertRubroToRubroModel(rubroRepository.findRubroById(id));
		}
		return new RubroModel();
	}

	@Override
	public RubroModel deshabilitarRubro(String id) {
		Rubro Rubro = rubroRepository.findRubroById(id);
		Rubro.setFechaBaja(new java.util.Date());
		rubroRepository.save(Rubro);
		return rubroConverter.convertRubroToRubroModel(Rubro);
	}
}
