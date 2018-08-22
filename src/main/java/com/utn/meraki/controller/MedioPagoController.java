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

import com.utn.meraki.model.MedioPagoModel;
import com.utn.meraki.service.MedioPagoService;

@RequestMapping("/mediopago")
@RestController
@CrossOrigin

public class MedioPagoController {
	
	//SERVICE
	@Autowired
	MedioPagoService medioPagoService;
	
	//CONTROLADORES
	@PostMapping("/crearMedioPago")
	public MedioPagoModel crearMedioPago(@RequestBody MedioPagoModel medioPagoModel) {
		return medioPagoService.crearMedioPago(medioPagoModel);
	}
	
	@PutMapping("/editarMedioPago")
	public MedioPagoModel editarMedioPago(@RequestBody MedioPagoModel medioPagoModel) {
		return medioPagoService.editarMedioPago(medioPagoModel);
	}
	
	@GetMapping("/listMedioPago")
	public List<MedioPagoModel> listMedioPagoVigente(){
		return medioPagoService.listMedioPagoVigente();
	}
	
	@PutMapping("/habilitarMedioPago")
	public MedioPagoModel habilitarMedioPago(@RequestParam(value="id",required=true)String id) {
		return medioPagoService.habilitarMedioPago(id);
	}
	
	@PutMapping("/deshabilitarMedioPago")
    public MedioPagoModel deshabilitarMedioPago(@RequestParam(value="id",required=true)String id) {
        return medioPagoService.deshabilitarMedioPago(id);
    }
	
	
	@GetMapping("/listAllMedioPago")
    public List<MedioPagoModel> listAllMedioPago(){
        return medioPagoService.listMedioPagoTodas();
    }
	
	@GetMapping
    public MedioPagoModel getMedioPagoById(@RequestParam(value="id",required=true)String id){
        return medioPagoService.getMedioPagoById(id);
    }

}
