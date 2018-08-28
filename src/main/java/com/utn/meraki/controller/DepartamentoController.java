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

import com.utn.meraki.model.DepartamentoModel;
import com.utn.meraki.service.DepartamentoService;

@RequestMapping("/departamento")
@RestController
@CrossOrigin

public class DepartamentoController {
	
	//SERVICIO
	@Autowired
	DepartamentoService departamentoService;
	
	//CONTROLADORES
	@PostMapping("/crearDepartamento")
	public DepartamentoModel crearDepartamento(@RequestBody DepartamentoModel departamentoModel) {
		return departamentoService.crearDepartamento(departamentoModel);
	}
	
	@PutMapping("/editarDepartamento")
	public DepartamentoModel editarDepartamento(@RequestBody DepartamentoModel departamentoModel) {
		return departamentoService.editarDepartamento(departamentoModel);
	}
	
	@PutMapping("/habilitarDepartamento")
	public DepartamentoModel habilitarDepartamento(@RequestParam(value="id",required=true)String id) {
		return departamentoService.habilitarDepartamento(id);
	}
	
	@GetMapping("/listDepartamentoVigente")
	public List<DepartamentoModel> listDepartamentoVigente(){
		return departamentoService.listDepartamentoVigente();
	}

	@GetMapping("/listAllDepartamentos")
	public List<DepartamentoModel> listAllDepartamentos(){
		return departamentoService.listDepartamentoTodos();
	}

	@GetMapping
	public DepartamentoModel getDepartamentoById(@RequestParam(value="id",required=true)String id){
		return departamentoService.getDepartamentoById(id);
	}
	@PutMapping("/deshabilitarDepartamento")
	public DepartamentoModel deshabilitarDepartamento(@RequestParam(value="id",required=true)String id) {
		return departamentoService.deshabilitarDepartamento(id);
	}

}
