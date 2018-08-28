package com.utn.meraki.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.converter.EstadoDestacadoConverter;
import com.utn.meraki.entity.EstadoDestacado;
import com.utn.meraki.model.EstadoDestacadoModel;
import com.utn.meraki.repository.EstadoDestacadoRepository;
import com.utn.meraki.service.EstadoDestacadoService;

@Service("estadoDestacadoService")

public class EstadoDestacadoServiceImpl implements EstadoDestacadoService{

	//CONVERTER
	@Autowired
	EstadoDestacadoConverter estadoDestacadoConverter;
	
	//REPOSITORY
	@Autowired
	EstadoDestacadoRepository estadoDestacadoRepository;
	
	//SERVICIOS
	@Override //Me crea un nuevo estado destacado
	public EstadoDestacadoModel crearEstadoDestacado(EstadoDestacadoModel estadoDestacadoModel) {
		EstadoDestacado estadoDestacado = estadoDestacadoConverter.convertEstadoDestacadoModelToEstadoDestacado(estadoDestacadoModel);
		return estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacado);
	}

	@Override //Me edita un estado destacado ya existente
	public EstadoDestacadoModel editarEstadoDestacado(EstadoDestacadoModel estadoDestacadoModel) {
		EstadoDestacado estadoDestacado = estadoDestacadoConverter.convertEstadoDestacadoModelToEstadoDestacado(estadoDestacadoModel);
		return estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacado);
	}

	@Override //Me muestra todas los estados destacados
	public List<EstadoDestacadoModel> listEstadoDestacadoVigente() {
		List<EstadoDestacadoModel> listEstadoDestacado = new ArrayList<>();
		for(EstadoDestacado estadoDestacado : estadoDestacadoRepository.findAll()) {
			if(estadoDestacado.getFechaBaja()==null) {
				listEstadoDestacado.add(estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacado));
			}
		}
		return listEstadoDestacado;
	}

	@Override //Me habilita un estado destacado que habia sido dado de baja
	public EstadoDestacadoModel habilitarEstadoDestacado(String id) {
		EstadoDestacado estadoDestacado = estadoDestacadoRepository.findEstadoDestacadoById(id);
		estadoDestacado.setFechaBaja(null);
		estadoDestacadoRepository.save(estadoDestacado);
		return estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacado);
	}
	
	//Me deshabilita un estado destacado que habia sido dado de alta
	@Override
    public EstadoDestacadoModel deshabilitarEstadoDestacado(String id) {
		EstadoDestacado estadoDestacado = estadoDestacadoRepository.findEstadoDestacadoById(id);
		estadoDestacado.setFechaBaja(new Date());
		estadoDestacadoRepository.save(estadoDestacado);
        return estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacado);
    }
	
	//busqueda de estado destacado por id
	 @Override
	    public EstadoDestacadoModel getEstadoDestacadoById(String id) {
	        if(id !=null){
	            return estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacadoRepository.findEstadoDestacadoById(id));
	        }
	        return new EstadoDestacadoModel();
	    }
	 
	 @Override
	    public List<EstadoDestacadoModel> listEstadoDestacadoTodas() {
	        List<EstadoDestacadoModel> listEstadoDestacado = new ArrayList<>();
	        for(EstadoDestacado estadoDestacado  : estadoDestacadoRepository.findAll()) {
	        	listEstadoDestacado.add(estadoDestacadoConverter.convertEstadoDestacadoToEstadoDestacadoModel(estadoDestacado));
	        }
	        return listEstadoDestacado;
	    }

}