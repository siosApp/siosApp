package com.utn.meraki.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utn.meraki.model.TipoRubroModel;
import com.utn.meraki.service.TipoRubroService;

@RequestMapping("/tipoRubro")
@RestController
@CrossOrigin

public class TipoRubroController {
	
	//SERVICE
	@Autowired
	TipoRubroService tipoRubroService;
	
	//CONTROLADORES
	@PostMapping("/crearTipoRubro")
	public TipoRubroModel crearTipoRubro(@RequestBody TipoRubroModel tipoRubroModel) {
		return tipoRubroService.crearTipoRubro(tipoRubroModel);
	}
	
	@PutMapping("/editarTipoRubro")
	public TipoRubroModel editarTipoRubro(@RequestBody TipoRubroModel tipoRubroModel) {
		return tipoRubroService.editarTipoRubro(tipoRubroModel);
	}
	
	@GetMapping("/listTipoRubroVigente")
	public List<TipoRubroModel> listTipoRubroVigente(){
		return tipoRubroService.listTipoRubroVigente();
	}
	
	@PutMapping("/bajaTipoRubro")
	public TipoRubroModel bajaTipoRubro(@RequestBody TipoRubroModel tipoRubroModel) {
		return tipoRubroService.bajaTipoRubro(tipoRubroModel);
	}
}
