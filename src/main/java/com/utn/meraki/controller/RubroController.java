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

import com.utn.meraki.model.RubroModel;
import com.utn.meraki.service.RubroService;

@RequestMapping("/rubro")
@RestController
@CrossOrigin

public class RubroController {
	
	//SERVICE
	@Autowired
	RubroService rubroService;
	
	//CONTROLADORES
	@PostMapping("/crearRubro")
	public RubroModel crearRubro(@RequestBody RubroModel rubroModel) {
		return rubroService.crearRubro(rubroModel);
	}
	
	@PutMapping("/editarRubro")
	public RubroModel editarRubro(@RequestBody RubroModel rubroModel) {
		return rubroService.editarRubro(rubroModel);
	}
	
	@PutMapping("/bajaRubro")
	public RubroModel bajaRubro(@RequestParam(value="id",required=true)String id) {
		return rubroService.bajaRubro(id);
	}
	
	@GetMapping("/listRubroVigente")
	public List<RubroModel> listRubroVigente(){
		return rubroService.listRubroVigente();
	}

}
