package com.utn.meraki.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.TipoRubroConverter;
import com.utn.meraki.entity.TipoRubro;
import com.utn.meraki.model.TipoRubroModel;
import com.utn.meraki.repository.TipoRubroRepository;
import com.utn.meraki.service.TipoRubroService;

@Service("tipoRubroService")

public class TipoRubroServiceImpl implements TipoRubroService{

	//CONVERTER
	@Autowired
	TipoRubroConverter tipoRubroConverter;
	
	//REPOSITORY
	@Autowired
	TipoRubroRepository tipoRubroRepository;
	
	//SERVICIOS
	@Override //Me crea un nuevo tipo de rubro
	public TipoRubroModel crearTipoRubro(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro= tipoRubroConverter.convertTipoRubroModelToTipoRubro(tipoRubroModel);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}

	@Override //Me edita un tipo de rubro existente
	public TipoRubroModel editarTipoRubro(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro = tipoRubroConverter.convertTipoRubroModelToTipoRubroEditado(tipoRubroModel);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}

	@Override //Me muestra todos los tipos de rubros que esten vigentes
	public List<TipoRubroModel> listTipoRubroVigente() {
		List<TipoRubroModel> listTipoRubro = new ArrayList<>();
		for(TipoRubro tipoRubro : tipoRubroRepository.findAll()) {
			if(tipoRubro.getFechaBaja()==null) {
				listTipoRubro.add(tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro));
			}
		}
		return listTipoRubro;
	}

	@Override //Me da de baja un tipo de rubro
	public TipoRubroModel bajaTipoRubro(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro = tipoRubroRepository.findTipoRubroById(tipoRubroModel.getId());
		tipoRubro.setFechaBaja(new Date(System.currentTimeMillis()));
		tipoRubroRepository.save(tipoRubro);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}

}
