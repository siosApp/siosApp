package com.utn.meraki.service;

import com.utn.meraki.model.FiltroModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.model.UsuarioModel;

import java.util.List;

public interface UsuarioService {
    
    public abstract UsuarioModel crearUsuario(UsuarioModel rubroModel);

    public abstract UsuarioModel editarUsuario(UsuarioModel rubroModel);

    public abstract List<UsuarioModel> listUsuarioVigente();

    public abstract UsuarioModel habilitarUsuario(String id);

    public abstract List<UsuarioModel> listUsuarioTodas();

    public abstract UsuarioModel getUsuarioById(String id);

    public abstract UsuarioModel deshabilitarUsuario(String id);
    
    public abstract UsuarioModel loguearUsuario(String username, String password);

    public abstract UsuarioModel existeUsuario(String username);
    
    public abstract List<UsuarioDestacadoModel> filtrarUsuarios(FiltroModel filtroModel);
    
}
