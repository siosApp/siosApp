package com.utn.meraki.service.impl;

import com.utn.meraki.converter.TipoUsuarioConverter;
import com.utn.meraki.entity.TipoUsuario;
import com.utn.meraki.model.TipoUsuarioModel;
import com.utn.meraki.repository.TipoUsuarioRepository;
import com.utn.meraki.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("tipoUsuarioService")
public class TIpoUsuarioServiceImpl implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioConverter tipoUsuarioConverter;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuarioModel crearTipoUsuario(TipoUsuarioModel tipoUsuarioModel) {
        TipoUsuario tipoUsuario = tipoUsuarioConverter.convertTipoUsuarioModelToTipoUsuario(tipoUsuarioModel);
        return tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuario);
    }

    @Override
    public TipoUsuarioModel editarTipoUsuario(TipoUsuarioModel tipoUsuarioModel) {
        TipoUsuario tipoUsuario = tipoUsuarioConverter.convertTipoUsuarioModelToTipoUsuario(tipoUsuarioModel);
        return tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuario);
    }

    @Override
    public List<TipoUsuarioModel> listTipoUsuarioVigente() {
        List<TipoUsuarioModel> listEstadosSolicitud = new ArrayList<>();
        for(TipoUsuario tipoUsuario : tipoUsuarioRepository.findAll()) {
            if(tipoUsuario.getFechaBaja()==null) {
                listEstadosSolicitud.add(tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuario));
            }
        }
        return listEstadosSolicitud;
    }

    @Override
    public TipoUsuarioModel habilitarTipoUsuario(String id) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findTipoUsuarioById(id);
        tipoUsuario.setFechaBaja(null);
        tipoUsuarioRepository.save(tipoUsuario);
        return tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuario);
    }

    @Override
    public List<TipoUsuarioModel> listTipoUsuarioTodas() {
        List<TipoUsuarioModel> listEstadosSolicitud = new ArrayList<>();
        for(TipoUsuario tipoUsuario : tipoUsuarioRepository.findAll()) {
            listEstadosSolicitud.add(tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuario));
        }
        return listEstadosSolicitud;
    }

    @Override
    public TipoUsuarioModel getTipoUsuarioById(String id) {
        if(id !=null){
            return tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuarioRepository.findTipoUsuarioById(id));
        }
        return new TipoUsuarioModel();
    }

    @Override
    public TipoUsuarioModel deshabilitarTipoUsuario(String id) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findTipoUsuarioById(id);
        tipoUsuario.setFechaBaja(new Date());
        tipoUsuarioRepository.save(tipoUsuario);
        return tipoUsuarioConverter.convertTipoUsuarioToTipoUsuarioModel(tipoUsuario);
    }
}
