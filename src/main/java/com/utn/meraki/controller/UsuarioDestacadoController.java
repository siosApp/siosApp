package com.utn.meraki.controller;

import java.util.List;

import com.utn.meraki.model.FiltroModel;
import com.utn.meraki.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.utn.meraki.model.DestacadoModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.service.DestacadoService;

@RequestMapping("/usuarioDestacado")
@RestController
@CrossOrigin

public class UsuarioDestacadoController {
	
	//SERVICE
	@Autowired
	DestacadoService destacadoService;
	@Autowired
	UsuarioService usuarioService;
	//CONTROLADORES
	@GetMapping("/listUltimosDestacados")
	public List<UsuarioDestacadoModel> listUltimosDestacados(){
		return destacadoService.listUltimosDestacados();
	}
	
	@PostMapping("/destacarPerfil")
	public DestacadoModel destacarPerfil(@RequestBody DestacadoModel destacadoModel) {
		return destacadoService.destacarPerfil(destacadoModel);
	}

	@GetMapping("/listOferentesDestacados")
	public List<UsuarioDestacadoModel> getOferentesDestacados(@RequestParam(value="tipoRubro",required = true)String tipoRubro,
															  @RequestParam(value="rubro",required = true)String rubro,
															  @RequestParam(value = "provincia",required = true)String provincia,
															  @RequestParam(value = "departamento",required = true)String departamento,
															  @RequestParam(value = "localidad",required = true)String localidad){
		FiltroModel filtroModel= new FiltroModel();
		filtroModel.setNombreDepartamento(departamento.equals("null")?null:departamento);
		filtroModel.setNombreLocalidad(localidad.equals("null") ? null:localidad);
		filtroModel.setNombreProvincia(provincia.equals("null")? null:provincia);
		filtroModel.setNombreRubro(rubro.equals("null")? null:rubro);
		filtroModel.setNombreTipoRubro(tipoRubro.equals("null")?null:tipoRubro);
		return usuarioService.filtrarUsuarios(filtroModel);
	}
}
