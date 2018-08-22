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

import com.utn.meraki.model.EstadoDestacadoModel;
import com.utn.meraki.service.EstadoDestacadoService;

@RequestMapping("/estadodestacado")
@RestController
@CrossOrigin

public class EstadoDestacadoController {
	
	//SERVICE
	@Autowired
	EstadoDestacadoService estadoDestacadoService;
	
	//CONTROLADORES
	@PostMapping("/crearEstadoDestacado")
	public EstadoDestacadoModel crearEstadoDestacado(@RequestBody EstadoDestacadoModel estadoDestacadoModel) {
		return estadoDestacadoService.crearEstadoDestacado(estadoDestacadoModel);
	}
	
	@PutMapping("/editarEstadoDestacado")
	public EstadoDestacadoModel editarEstadoDestacado(@RequestBody EstadoDestacadoModel estadoDestacadoModel) {
		return estadoDestacadoService.editarEstadoDestacado(estadoDestacadoModel);
	}
	
	@GetMapping("/listEstadoDestacadoVigente")
	public List<EstadoDestacadoModel> listEstadoDestacadoVigente(){
		return estadoDestacadoService.listEstadoDestacadoVigente();
	}
	
	@PutMapping("/habilitarEstadoDestacado")
	public EstadoDestacadoModel habilitarEstadoDestacado(@RequestParam(value="id",required=true)String id) {
		return estadoDestacadoService.habilitarEstadoDestacado(id);
	}
	
	@PutMapping("/deshabilitarEstadoDestacado")
    public EstadoDestacadoModel deshabilitarEstadoDestacado(@RequestParam(value="id",required=true)String id) {
        return estadoDestacadoService.deshabilitarEstadoDestacado(id);
    }
	
	
	@GetMapping("/listAllEstadoDestacado")
    public List<EstadoDestacadoModel> listAllEstadoDestacado(){
        return estadoDestacadoService.listEstadoDestacadoTodas();
    }
	
	@GetMapping
    public EstadoDestacadoModel getEstadoDestacadoById(@RequestParam(value="id",required=true)String id){
        return estadoDestacadoService.getEstadoDestacadoById(id);
    }

}
