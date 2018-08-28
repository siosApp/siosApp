package com.utn.meraki.service.impl;

import com.utn.meraki.converter.UsuarioConverter;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioConverter usuarioConverter;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioConverter.convertUsuarioModelToUsuario(usuarioModel);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public UsuarioModel editarUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioConverter.convertUsuarioModelToUsuario(usuarioModel);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public List<UsuarioModel> listUsuarioVigente() {
        List<UsuarioModel> listusuarios = new ArrayList<>();
        for(Usuario usuario : usuarioRepository.findAll()) {
            if(usuario.getFechaBaja()==null) {
                listusuarios.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
            }
        }
        return listusuarios;
    }

    @Override
    public UsuarioModel habilitarUsuario(String id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        usuario.setFechaBaja(null);
        usuarioRepository.save(usuario);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public List<UsuarioModel> listUsuarioTodas() {
        List<UsuarioModel> listUsuarios = new ArrayList<>();
        for(Usuario usuario : usuarioRepository.findAll()) {
            listUsuarios.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
        }
        return listUsuarios;
    }

    @Override
    public UsuarioModel getUsuarioById(String id) {
        if(id !=null){
            return usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findUsuarioById(id));
        }
        return new UsuarioModel();
    }

    @Override
    public UsuarioModel deshabilitarUsuario(String id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        usuario.setFechaBaja(new java.util.Date());
        usuarioRepository.save(usuario);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }
}
