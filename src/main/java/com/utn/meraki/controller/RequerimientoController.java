package com.utn.meraki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.service.RequerimientoService;

@RequestMapping("/requerimiento")
@RestController
@CrossOrigin

public class RequerimientoController {
	
	//SERVICE
	@Autowired
	RequerimientoService requerimientoService;
	
	//CONTROLADORES
	@PostMapping("/publicarRequerimiento")
	public RequerimientoModel publicarRequerimiento(@RequestBody RequerimientoModel requerimientoModel) {
		return requerimientoService.publicarRequerimiento(requerimientoModel);
	}

}
