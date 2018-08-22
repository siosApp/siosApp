package com.utn.meraki.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

	@PutMapping("/habilitarTipoRubro")
	public TipoRubroModel habilitarTipoRubro(@RequestParam(value="id",required=true)String id) {
		return tipoRubroService.habilitarTipoRubro(id);
	}

	@GetMapping("/listAllTipoRubro")
	public List<TipoRubroModel> listAllTipoRubro(){
		return tipoRubroService.listTipoRubroTodas();
	}

	@GetMapping
	public TipoRubroModel getTipoRubroById(@RequestParam(value="id",required=true)String id){
		return tipoRubroService.getTipoRubroById(id);
	}
	@PutMapping("/deshabilitarTipoRubro")
	public TipoRubroModel deshabilitarTipoRubro(@RequestParam(value="id",required=true)String id) {
		return tipoRubroService.deshabilitarTipoRubro(id);
	}
}
