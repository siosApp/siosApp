package com.utn.meraki.service;

import com.utn.meraki.model.EstadoRequerimientoModel;

import java.util.List;

public interface EstadoRequerimientoService {
    
    public abstract EstadoRequerimientoModel crearEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel);

    public abstract EstadoRequerimientoModel editarEstadoRequerimiento(EstadoRequerimientoModel estadoRequerimientoModel);

    public abstract List<EstadoRequerimientoModel> listEstadoRequerimientoVigente();

    public abstract EstadoRequerimientoModel habilitarEstadoRequerimiento(String id);

    public abstract List<EstadoRequerimientoModel> listEstadoRequerimientoTodas();

    public abstract EstadoRequerimientoModel getEstadoById(String id);

    public abstract EstadoRequerimientoModel deshabilitarEstadoRequerimiento(String id);
}
