package com.utn.meraki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utn.meraki.model.EstadoRequerimientoModel;
import com.utn.meraki.service.EstadoRequerimientoService;

@RequestMapping("/estadoRequerimiento")
@RestController
@CrossOrigin

public class EstadoRequerimientoController {
	
	//SERVICE
	@Autowired
	EstadoRequerimientoService estadoRequerimientoService;
	
    //CONTROLADORES
    @PostMapping("/crearEstadoRequerimiento")
    public EstadoRequerimientoModel crearEstadoRequerimiento(@RequestBody EstadoRequerimientoModel estadoRequerimientoModel) {
        return estadoRequerimientoService.crearEstadoRequerimiento(estadoRequerimientoModel);
    }

    @PutMapping("/editarEstadoRequerimiento")
    public EstadoRequerimientoModel editarEstadoRequerimiento(@RequestBody EstadoRequerimientoModel estadoRequerimientoModel) {
        return estadoRequerimientoService.editarEstadoRequerimiento(estadoRequerimientoModel);
    }

    @GetMapping("/listEstadoRequerimientoVigente")
    public List<EstadoRequerimientoModel> listEstadoRequerimientoVigente(){
        return estadoRequerimientoService.listEstadoRequerimientoVigente();
    }

    @PutMapping("/habilitarEstadoRequerimiento")
    public EstadoRequerimientoModel habilitarEstadoRequerimiento(@RequestParam(value="id",required=true)String id) {
        return estadoRequerimientoService.habilitarEstadoRequerimiento(id);
    }

    @GetMapping("/listAllEstadoRequerimiento")
    public List<EstadoRequerimientoModel> listAllEstadoRequerimiento(){
        return estadoRequerimientoService.listEstadoRequerimientoTodas();
    }

    @GetMapping
    public EstadoRequerimientoModel getEstadoById(@RequestParam(value="id",required=true)String id){
        return estadoRequerimientoService.getEstadoById(id);
    }
    @PutMapping("/deshabilitarEstadoRequerimiento")
    public EstadoRequerimientoModel deshabilitarEstadoRequerimiento(@RequestParam(value="id",required=true)String id) {
        return estadoRequerimientoService.deshabilitarEstadoRequerimiento(id);
    }

}
