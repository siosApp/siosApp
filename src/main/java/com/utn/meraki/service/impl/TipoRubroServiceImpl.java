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


	@Autowired
	private TipoRubroConverter tipoRubroConverter;
	@Autowired
	private TipoRubroRepository tipoRubroRepository;

	@Override
	public TipoRubroModel crearTipoRubro(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro = tipoRubroConverter.convertTipoRubroModelToTipoRubro(tipoRubroModel);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}

	@Override
	public TipoRubroModel editarTipoRubro(TipoRubroModel tipoRubroModel) {
		TipoRubro tipoRubro = tipoRubroConverter.convertTipoRubroModelToTipoRubro(tipoRubroModel);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}

	@Override
	public List<TipoRubroModel> listTipoRubroVigente() {
		List<TipoRubroModel> listEstadosSolicitud = new ArrayList<>();
		for(TipoRubro tipoRubro : tipoRubroRepository.findAll()) {
			if(tipoRubro.getFechaBaja()==null) {
				listEstadosSolicitud.add(tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro));
			}
		}
		return listEstadosSolicitud;
	}

	@Override
	public TipoRubroModel habilitarTipoRubro(String id) {
		TipoRubro tipoRubro = tipoRubroRepository.findTipoRubroById(id);
		tipoRubro.setFechaBaja(null);
		tipoRubroRepository.save(tipoRubro);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}

	@Override
	public List<TipoRubroModel> listTipoRubroTodas() {
		List<TipoRubroModel> listEstadosSolicitud = new ArrayList<>();
		for(TipoRubro tipoRubro : tipoRubroRepository.findAll()) {
			listEstadosSolicitud.add(tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro));
		}
		return listEstadosSolicitud;
	}

	@Override
	public TipoRubroModel getTipoRubroById(String id) {
		if(id !=null){
			return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubroRepository.findTipoRubroById(id));
		}
		return new TipoRubroModel();
	}

	@Override
	public TipoRubroModel deshabilitarTipoRubro(String id) {
		TipoRubro tipoRubro = tipoRubroRepository.findTipoRubroById(id);
		tipoRubro.setFechaBaja(new java.util.Date());
		tipoRubroRepository.save(tipoRubro);
		return tipoRubroConverter.convertTipoRubroToTipoRubroModel(tipoRubro);
	}
}
