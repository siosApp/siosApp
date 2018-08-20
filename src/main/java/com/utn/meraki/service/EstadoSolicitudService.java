package com.utn.meraki.service;

import com.utn.meraki.entity.EstadoSolicitud;
import com.utn.meraki.model.EstadoSolicitudModel;

import java.util.List;

public interface EstadoSolicitudService {
    
    public abstract EstadoSolicitudModel crearEstadoSolicitud(EstadoSolicitudModel EstadoSolicitudModel);

    public abstract EstadoSolicitudModel editarEstadoSolicitud(EstadoSolicitudModel EstadoSolicitudModel);

    public abstract List<EstadoSolicitudModel> listEstadoSolicitudVigente();

    public abstract EstadoSolicitudModel habilitarEstadoSolicitud(String id);

    public abstract List<EstadoSolicitudModel> listEstadoSolicitudTodas();

    public abstract EstadoSolicitudModel getEstadoById(String id);

    public abstract EstadoSolicitudModel deshabilitarEstadoSolicitud(String id);
}
