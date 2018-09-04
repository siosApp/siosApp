package com.utn.meraki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	//CONTROLADORES
	@GetMapping("/listUltimosDestacados")
	public List<UsuarioDestacadoModel> listUltimosDestacados(){
		return destacadoService.listUltimosDestacados();
	}
	
	@PostMapping("/destacarPerfil")
	public DestacadoModel destacarPerfil(@RequestBody DestacadoModel destacadoModel) {
		return destacadoService.destacarPerfil(destacadoModel);
	}

}
