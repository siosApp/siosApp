package com.utn.meraki.service.impl;

import com.utn.meraki.converter.EstadoSolicitudConverter;
import com.utn.meraki.entity.EstadoSolicitud;
import com.utn.meraki.model.EstadoSolicitudModel;
import com.utn.meraki.repository.EstadoSolicitudRepository;
import com.utn.meraki.service.EstadoSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("estadoSolicitudService")
public class EstadoSolicitudImpl implements EstadoSolicitudService {

    @Autowired
    private EstadoSolicitudConverter estadoSolicitudConverter;
    @Autowired
    private EstadoSolicitudRepository estadoSolicitudRepository;

    @Override
    public EstadoSolicitudModel crearEstadoSolicitud(EstadoSolicitudModel estadoSolicitudModel) {
        EstadoSolicitud estadoSolicitud = estadoSolicitudConverter.convertEstadoSolicitudModelToEstadoSolicitud(estadoSolicitudModel);
        return estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitud);
    }

    @Override
    public EstadoSolicitudModel editarEstadoSolicitud(EstadoSolicitudModel estadoSolicitudModel) {
        EstadoSolicitud estadoSolicitud = estadoSolicitudConverter.convertEstadoSolicitudModelToEstadoSolicitud(estadoSolicitudModel);
        return estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitud);
    }

    @Override
    public List<EstadoSolicitudModel> listEstadoSolicitudVigente() {
        List<EstadoSolicitudModel> listEstadosSolicitud = new ArrayList<>();
        for(EstadoSolicitud estadoSolicitud : estadoSolicitudRepository.findAll()) {
            if(estadoSolicitud.getFechaBaja()==null) {
                listEstadosSolicitud.add(estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitud));
            }
        }
        return listEstadosSolicitud;
    }

    @Override
    public EstadoSolicitudModel habilitarEstadoSolicitud(String id) {
        EstadoSolicitud estadoSolicitud = estadoSolicitudRepository.findEstadoSolicitudById(id);
        estadoSolicitud.setFechaBaja(null);
        estadoSolicitudRepository.save(estadoSolicitud);
        return estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitud);
    }

    @Override
    public List<EstadoSolicitudModel> listEstadoSolicitudTodas() {
        List<EstadoSolicitudModel> listEstadosSolicitud = new ArrayList<>();
        for(EstadoSolicitud estadoSolicitud : estadoSolicitudRepository.findAll()) {
            listEstadosSolicitud.add(estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitud));
        }
        return listEstadosSolicitud;
    }

    @Override
    public EstadoSolicitudModel getEstadoById(String id) {
        if(id !=null){
            return estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitudRepository.findEstadoSolicitudById(id));
        }
        return new EstadoSolicitudModel();
    }

    @Override
    public EstadoSolicitudModel deshabilitarEstadoSolicitud(String id) {
        EstadoSolicitud estadoSolicitud = estadoSolicitudRepository.findEstadoSolicitudById(id);
        estadoSolicitud.setFechaBaja(new Date());
        estadoSolicitudRepository.save(estadoSolicitud);
        return estadoSolicitudConverter.convertEstadoSolicitudToEstadoSolicitudModel(estadoSolicitud);
    }
}
