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

import com.utn.meraki.model.LocalidadModel;
import com.utn.meraki.service.LocalidadService;

@RequestMapping("/localidad")
@RestController
@CrossOrigin

public class LocalidadController {
	
	//SERVICIO
	@Autowired
	LocalidadService localidadService;
	
	//CONTROLADORES
	@PostMapping("/crearLocalidad")
	public LocalidadModel crearLocalidad(@RequestBody LocalidadModel localidadModel) {
		return localidadService.crearLocalidad(localidadModel);
	}
	
	@PutMapping("/editarLocalidad")
	public LocalidadModel editarLocalidad(@RequestBody LocalidadModel localidadModel) {
		return localidadService.editarLocalidad(localidadModel);
	}
	
	@PutMapping("/habilitarLocalidad")
	public LocalidadModel habilitarLocalidad(@RequestParam(value="id",required=true)String id) {
		return localidadService.habilitarLocalidad(id);
	}
	
	@GetMapping("/listLocalidadVigente")
	public List<LocalidadModel> listLocalidadVigente(){
		return localidadService.listLocalidadVigente();
	}

	@GetMapping("/listLocalidades")
	public List<LocalidadModel> listLocalidadesVigenteByDepartamento(
			@RequestParam(value="provincia",required=true)String provincia,
			@RequestParam(value="departamento",required=true)String departamento){
		return localidadService.findLocalidadesByProvinciaAndDepartamento(provincia,departamento);
	}

	@GetMapping("/listAllLocalidades")
	public List<LocalidadModel> listAllLocalidades(){
		return localidadService.listLocalidadTodos();
	}

	@GetMapping
	public LocalidadModel getLocalidadById(@RequestParam(value="id",required=true)String id){
		return localidadService.getLocalidadById(id);
	}
	@PutMapping("/deshabilitarLocalidad")
	public LocalidadModel deshabilitarLocalidad(@RequestParam(value="id",required=true)String id) {
		return localidadService.deshabilitarLocalidad(id);
	}

	@GetMapping("/domicilio")
	public LocalidadModel getLocalidadByDomicilio(@RequestParam(value="id",required=true)String id){
		return localidadService.getLocalidadByDomicilio(id);
	}
	@GetMapping("/localidad")
	public LocalidadModel findLocalidadByNombreLocalidadAndProvinciaAndDepartamento(
			@RequestParam(value="nombreLocalidad",required=true)String nombreLocalidad,
			@RequestParam(value="provincia",required=true)String provincia,
			@RequestParam(value="departamento",required=true)String departamento){
		return localidadService.findLocalidadesByNombreLocalidadProvinciaAndDepartamento(nombreLocalidad,provincia,departamento);
	}
}