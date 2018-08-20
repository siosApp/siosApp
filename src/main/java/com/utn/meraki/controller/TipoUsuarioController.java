package com.utn.meraki.controller;

import com.utn.meraki.model.TipoUsuarioModel;
import com.utn.meraki.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tipoUsuario")
@RestController
@CrossOrigin
public class TipoUsuarioController {

    //SERVICE
    @Autowired
    TipoUsuarioService tipoUsuarioService;

    //CONTROLADORES
    @PostMapping("/crearTipoUsuario")
    public TipoUsuarioModel crearTipoUsuario(@RequestBody TipoUsuarioModel tipoUsuarioModel) {
        return tipoUsuarioService.crearTipoUsuario(tipoUsuarioModel);
    }

    @PutMapping("/editarTipoUsuario")
    public TipoUsuarioModel editarTipoUsuario(@RequestBody TipoUsuarioModel tipoUsuarioModel) {
        return tipoUsuarioService.editarTipoUsuario(tipoUsuarioModel);
    }

    @GetMapping("/listTipoUsuarioVigente")
    public List<TipoUsuarioModel> listTipoUsuarioVigente(){
        return tipoUsuarioService.listTipoUsuarioVigente();
    }

    @PutMapping("/habilitarTipoUsuario")
    public TipoUsuarioModel habilitarTipoUsuario(@RequestParam(value="id",required=true)String id) {
        return tipoUsuarioService.habilitarTipoUsuario(id);
    }

    @GetMapping("/listAllTipoUsuario")
    public List<TipoUsuarioModel> listAllTipoUsuario(){
        return tipoUsuarioService.listTipoUsuarioTodas();
    }

    @GetMapping
    public TipoUsuarioModel getEstadoById(@RequestParam(value="id",required=true)String id){
        return tipoUsuarioService.getTipoUsuarioById(id);
    }
    @PutMapping("/deshabilitarTipoUsuario")
    public TipoUsuarioModel deshabilitarTipoUsuario(@RequestParam(value="id",required=true)String id) {
        return tipoUsuarioService.deshabilitarTipoUsuario(id);
    }
}
