package com.utn.meraki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.utn.meraki.model.CalificacionModel;
import com.utn.meraki.model.CalificacionUsuarioModel;
import com.utn.meraki.service.CalificacionService;

@RequestMapping("/calificacion")
@RestController
@CrossOrigin

public class CalificacionController {
	
	//SERVICE
	@Autowired
	CalificacionService calificacionService;
	
	//CONTROLADORES
	@PutMapping("/realizarCalificacion")
	public CalificacionModel realizarCalificacion(@RequestBody CalificacionModel calificacionModel) {
		return calificacionService.realizarCalificacion(calificacionModel);
	}
	
	@GetMapping("/calificacionesUsuario")
	public List<CalificacionUsuarioModel> calificacionUsuario(@RequestParam(value="idUsuario",required=true)String idUsuario){
		return calificacionService.calificacionesUsuario(idUsuario);
	}

}
