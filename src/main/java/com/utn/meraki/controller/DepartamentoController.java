package com.utn.meraki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/listDepartamentosVigente")
	public List<DepartamentoModel> listDepartamentosVigentesByProvincia(@RequestParam(value="provincia",required=true)String provincia){
		return departamentoService.listDepartamentoVigenteByProvincia(provincia);
	}

	@GetMapping("/listAllDepartamentos")
	public List<DepartamentoModel> listAllDepartamentos(){
		return departamentoService.listDepartamentoTodos();
	}

	@GetMapping
	public DepartamentoModel getDepartamentoById(@RequestParam(value="id",required=true)String id){
		return departamentoService.getDepartamentoById(id);
	}
	@GetMapping("/departamento")
	public DepartamentoModel getDepartamentoByNombreAndProvincia(@RequestParam(value="nombre",required=true)String departamento,@RequestParam(value="provincia",required=true)String nombreProvincia){
		return departamentoService.getDepartamentoByNombreAndProvincia(departamento,nombreProvincia);
	}
	@GetMapping("/departamentoByNombre")
	public DepartamentoModel getDepartamentoByNombre(@RequestParam(value="nombre",required=true)String nombre){
		return departamentoService.getDepartamentoByNombre(nombre);
	}
	@PutMapping("/deshabilitarDepartamento")
	public DepartamentoModel deshabilitarDepartamento(@RequestParam(value="id",required=true)String id) {
		return departamentoService.deshabilitarDepartamento(id);
	}

}
