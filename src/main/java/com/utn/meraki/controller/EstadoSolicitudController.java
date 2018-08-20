package com.utn.meraki.controller;

import com.utn.meraki.model.EstadoSolicitudModel;
import com.utn.meraki.service.EstadoSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/estadoSolicitud")
@RestController
@CrossOrigin
public class EstadoSolicitudController {

    //SERVICE
    @Autowired
    EstadoSolicitudService estadoSolicitudService;

    //CONTROLADORES
    @PostMapping("/crearEstadoSolicitud")
    public EstadoSolicitudModel crearEstadoSolicitud(@RequestBody EstadoSolicitudModel estadoSolicitudModel) {
        return estadoSolicitudService.crearEstadoSolicitud(estadoSolicitudModel);
    }

    @PutMapping("/editarEstadoSolicitud")
    public EstadoSolicitudModel editarEstadoSolicitud(@RequestBody EstadoSolicitudModel estadoSolicitudModel) {
        return estadoSolicitudService.editarEstadoSolicitud(estadoSolicitudModel);
    }

    @GetMapping("/listEstadoSolicitudVigente")
    public List<EstadoSolicitudModel> listEstadoSolicitudVigente(){
        return estadoSolicitudService.listEstadoSolicitudVigente();
    }

    @PutMapping("/habilitarEstadoSolicitud")
    public EstadoSolicitudModel habilitarEstadoSolicitud(@RequestParam(value="id",required=true)String id) {
        return estadoSolicitudService.habilitarEstadoSolicitud(id);
    }

    @GetMapping("/listAllEstadoSolicitud")
    public List<EstadoSolicitudModel> listAllEstadoSolicitud(){
        return estadoSolicitudService.listEstadoSolicitudTodas();
    }

    @GetMapping
    public EstadoSolicitudModel getEstadoById(@RequestParam(value="id",required=true)String id){
        return estadoSolicitudService.getEstadoById(id);
    }
    @PutMapping("/deshabilitarEstadoSolicitud")
    public EstadoSolicitudModel deshabilitarEstadoSolicitud(@RequestParam(value="id",required=true)String id) {
        return estadoSolicitudService.deshabilitarEstadoSolicitud(id);
    }
}
