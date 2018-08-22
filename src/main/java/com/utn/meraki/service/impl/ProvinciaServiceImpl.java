package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.ProvinciaConverter;
import com.utn.meraki.entity.Provincia;
import com.utn.meraki.model.ProvinciaModel;
import com.utn.meraki.repository.ProvinciaRepository;
import com.utn.meraki.service.ProvinciaService;

@Service("provinciaService")

public class ProvinciaServiceImpl implements ProvinciaService{

	//CONVERTER
	@Autowired
	ProvinciaConverter provinciaConverter;
	
	//REPOSITORY
	@Autowired
	ProvinciaRepository provinciaRepository;
	
	//SERVICIOS
	@Override //Me crea una nueva provincia
	public ProvinciaModel crearProvincia(ProvinciaModel provinciaModel) {
		Provincia provincia = provinciaConverter.convertProvinciaModelToProvincia(provinciaModel);
		return provinciaConverter.convertProvinciaToProvinciaModel(provincia);
	}

	@Override //Me edita una provincia ya existente
	public ProvinciaModel editarProvincia(ProvinciaModel provinciaModel) {
		Provincia provincia = provinciaConverter.convertProvinciaModelToProvincia(provinciaModel);
		return provinciaConverter.convertProvinciaToProvinciaModel(provincia);
	}

	@Override //Me muestra todas las provincias vigentes
	public List<ProvinciaModel> listProvinciaVigente() {
		List<ProvinciaModel> listProvincias = new ArrayList<>();
		for(Provincia provincia : provinciaRepository.findAll()) {
			if(provincia.getFechaBaja()==null) {
				listProvincias.add(provinciaConverter.convertProvinciaToProvinciaModel(provincia));
			}
		}
		return listProvincias;
	}

	@Override //Me habilita una provincia que habia sido dado de baja
	public ProvinciaModel habilitarProvincia(String id) {
		Provincia provincia = provinciaRepository.findProvinciaById(id);
		provincia.setFechaBaja(null);
		provinciaRepository.save(provincia);
		return provinciaConverter.convertProvinciaToProvinciaModel(provincia);
	}
	
	//Me deshabilita una provincia que habia sido dado de alta
	@Override
    public ProvinciaModel deshabilitarProvincia(String id) {
        Provincia provincia = provinciaRepository.findProvinciaById(id);
        provincia.setFechaBaja(new Date());
        provinciaRepository.save(provincia);
        return provinciaConverter.convertProvinciaToProvinciaModel(provincia);
    }
	
	//busqueda de provincia por id
	 @Override
	    public ProvinciaModel getProvinciaById(String id) {
	        if(id !=null){
	            return provinciaConverter.convertProvinciaToProvinciaModel(provinciaRepository.findProvinciaById(id));
	        }
	        return new ProvinciaModel();
	    }
	 
	 @Override
	    public List<ProvinciaModel> listProvinciaTodas() {
	        List<ProvinciaModel> listProvincia = new ArrayList<>();
	        for(Provincia provincia : provinciaRepository.findAll()) {
	            listProvincia.add(provinciaConverter.convertProvinciaToProvinciaModel(provincia));
	        }
	        return listProvincia;
	    }

}
