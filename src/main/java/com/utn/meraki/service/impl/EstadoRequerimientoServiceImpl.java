package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.EstadoRequerimientoConverter;
import com.utn.meraki.entity.EstadoRequerimiento;
import com.utn.meraki.model.EstadoRequerimientoModel;
import com.utn.meraki.repository.EstadoRequerimientoRepository;
import com.utn.meraki.service.EstadoRequerimientoService;

@Service("EstadoRequerimientoService")

public class EstadoRequerimientoServiceImpl implements EstadoRequerimientoService {

    @Autowired
    private EstadoRequerimientoConverter estadoRequerimientoConverter;
    @Autowired
    private EstadoRequerimientoRepository estadoRequerimientoRepository;

    @Override
    public EstadoRequerimientoModel crearEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel) {
        EstadoRequerimiento estadoRequerimiento = estadoRequerimientoConverter.convertEstadoReqModelToEstadoReq(estadoRequerimientoModel);
        return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
    }

    @Override
    public EstadoRequerimientoModel editarEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel) {
        EstadoRequerimiento estadoRequerimiento = estadoRequerimientoConverter.convertEstadoReqModelToEstadoReq(estadoRequerimientoModel);
        return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
    }

    @Override
    public List<EstadoRequerimientoModel> listEstadoRequerimientoVigente() {
        List<EstadoRequerimientoModel> listEstadosRequerimiento = new ArrayList<>();
        for(EstadoRequerimiento estadoRequerimiento : estadoRequerimientoRepository.findAll()) {
            if(estadoRequerimiento.getFechaBaja()==null) {
                listEstadosRequerimiento.add(estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento));
            }
        }
        return listEstadosRequerimiento;
    }

    @Override
    public EstadoRequerimientoModel habilitarEstadoRequerimiento(String id) {
        EstadoRequerimiento estadoRequerimiento = estadoRequerimientoRepository.findEstadoRequerimientoById(id);
        estadoRequerimiento.setFechaBaja(null);
        estadoRequerimientoRepository.save(estadoRequerimiento);
        return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
    }

    @Override
    public List<EstadoRequerimientoModel> listEstadoRequerimientoTodas() {
        List<EstadoRequerimientoModel> listEstadosRequerimiento = new ArrayList<>();
        for(EstadoRequerimiento estadoRequerimiento : estadoRequerimientoRepository.findAll()) {
            listEstadosRequerimiento.add(estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento));
        }
        return listEstadosRequerimiento;
    }

    @Override
    public EstadoRequerimientoModel getEstadoById(String id) {
        if(id !=null){
            return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimientoRepository.findEstadoRequerimientoById(id));
        }
        return new EstadoRequerimientoModel();
    }

    @Override
    public EstadoRequerimientoModel deshabilitarEstadoRequerimiento(String id) {
        EstadoRequerimiento estadoRequerimiento = estadoRequerimientoRepository.findEstadoRequerimientoById(id);
        estadoRequerimiento.setFechaBaja(new Date());
        estadoRequerimientoRepository.save(estadoRequerimiento);
        return estadoRequerimientoConverter.convertEstadoReqToEstadoReqModel(estadoRequerimiento);
    }

}

