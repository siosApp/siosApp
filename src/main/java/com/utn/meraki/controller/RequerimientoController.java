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
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.service.RequerimientoService;

@RequestMapping("/requerimiento")
@RestController
@CrossOrigin


public class RequerimientoController {
	
	//SERVICIO
	@Autowired
	RequerimientoService requerimientoService;
	
	//CONTROLADORES
		@PostMapping("/crearRequerimiento")
		public RequerimientoModel crearRequerimiento(@RequestBody RequerimientoModel requerimientoModel) {
			return requerimientoService.crearRequerimiento(requerimientoModel);
		}

}
