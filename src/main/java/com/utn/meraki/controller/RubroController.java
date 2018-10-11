package com.utn.meraki.controller;

import java.util.List;

import com.utn.meraki.model.CertificadoModel;
import com.utn.meraki.model.ExperienciaModel;
import com.utn.meraki.model.UsuarioRubroModel;
import com.utn.meraki.service.RubroService;
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

	@GetMapping("/listRubroVigente")
	public List<RubroModel> listRubroVigente(){
		return rubroService.listRubroVigente();
	}

	@PutMapping("/habilitarRubro")
	public RubroModel habilitarRubro(@RequestParam(value="id",required=true)String id) {
		return rubroService.habilitarRubro(id);
	}

	@GetMapping("/listAllRubro")
	public List<RubroModel> listAllRubro(){
		return rubroService.listRubroTodas();
	}

	@GetMapping
	public RubroModel getRubroById(@RequestParam(value="id",required=true)String id){
		return rubroService.getRubroById(id);
	}
	@PutMapping("/deshabilitarRubro")
	public RubroModel deshabilitarRubro(@RequestParam(value="id",required=true)String id) {
		return rubroService.deshabilitarRubro(id);
	}

	@GetMapping("/listRubrosByTipoRubro")
	public List<RubroModel> getRubrosByTipoRubro(@RequestParam(value="tipoRubro",required=true)String tipoRubro){
		return rubroService.listRubroVigenteByTipoRubro(tipoRubro);
	}
	
	@GetMapping("/getRubroByNombre")
	public RubroModel getRubroByNombre(@RequestParam(value="nombreRubro",required=true)String nombreRubro) {
		return rubroService.getRubroByNombreRubro(nombreRubro);
	}

	@PostMapping("/anadirOrEliminarCertificado")
	public UsuarioRubroModel addCertificado(@RequestParam(value="idUsuarioRubro",required=true) String idUsuarioRubro,
											@RequestBody CertificadoModel certificadoModel){
		return rubroService.anadirOrEliminarCertificado(idUsuarioRubro,certificadoModel);
	}

	@PostMapping("/anadirOrEliminarExperiencia")
	public UsuarioRubroModel addExperiencia(@RequestParam(value="idUsuarioRubro",required=true) String idUsuarioRubro,
											@RequestBody ExperienciaModel experienciaModel ){
		return rubroService.anadirOrEliminarExperiencia(idUsuarioRubro,experienciaModel);
	}
}
