package com.utn.meraki.service;

import com.utn.meraki.model.TipoUsuarioModel;

import java.util.List;

public interface TipoUsuarioService {
    public abstract TipoUsuarioModel crearTipoUsuario(TipoUsuarioModel tipoUsuarioModel);

    public abstract TipoUsuarioModel editarTipoUsuario(TipoUsuarioModel tipoUsuarioModel);

    public abstract List<TipoUsuarioModel> listTipoUsuarioVigente();

    public abstract TipoUsuarioModel habilitarTipoUsuario(String id);

    public abstract List<TipoUsuarioModel> listTipoUsuarioTodas();

    public abstract TipoUsuarioModel getTipoUsuarioById(String id);

    public abstract TipoUsuarioModel deshabilitarTipoUsuario(String id);
}
