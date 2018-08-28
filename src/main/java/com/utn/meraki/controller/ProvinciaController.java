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

import com.utn.meraki.model.ProvinciaModel;
import com.utn.meraki.service.ProvinciaService;

@RequestMapping("/provincia")
@RestController
@CrossOrigin

public class ProvinciaController {
	
	//SERVICE
	@Autowired
	ProvinciaService provinciaService;
	
	//CONTROLADORES
	@PostMapping("/crearProvincia")
	public ProvinciaModel crearProvincia(@RequestBody ProvinciaModel provinciaModel) {
		return provinciaService.crearProvincia(provinciaModel);
	}
	
	@PutMapping("/editarProvincia")
	public ProvinciaModel editarProvincia(@RequestBody ProvinciaModel provinciaModel) {
		return provinciaService.editarProvincia(provinciaModel);
	}
	
	@GetMapping("/listProvinciaVigente")
	public List<ProvinciaModel> listProvinciaVigente(){
		return provinciaService.listProvinciaVigente();
	}
	
	@PutMapping("/habilitarProvincia")
	public ProvinciaModel habilitarProvincia(@RequestParam(value="id",required=true)String id) {
		return provinciaService.habilitarProvincia(id);
	}
	
	@PutMapping("/deshabilitarProvincia")
    public ProvinciaModel deshabilitarProvina(@RequestParam(value="id",required=true)String id) {
        return provinciaService.deshabilitarProvincia(id);
    }
	
	
	@GetMapping("/listAllProvincia")
    public List<ProvinciaModel> listAllProvincia(){
        return provinciaService.listProvinciaTodas();
    }
	
	@GetMapping
    public ProvinciaModel getProvinciaById(@RequestParam(value="id",required=true)String id){
        return provinciaService.getProvinciaById(id);
    }

}
