package com.utn.meraki.controller;

import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
@CrossOrigin
public class UsuarioController {
    

    @Autowired
    UsuarioService usuarioService;

    //CONTROLADORES
    @PostMapping("/crearUsuario")
    public UsuarioModel crearUsuario(@RequestBody UsuarioModel usuarioModel) {
        return usuarioService.crearUsuario(usuarioModel);
    }

    @PutMapping("/editarUsuario")
    public UsuarioModel editarUsuario(@RequestBody UsuarioModel usuarioModel) {
        return usuarioService.editarUsuario(usuarioModel);
    }

    @GetMapping("/listUsuarioVigente")
    public List<UsuarioModel> listUsuarioVigente(){
        return usuarioService.listUsuarioVigente();
    }

    @PutMapping("/habilitarUsuario")
    public UsuarioModel habilitarUsuario(@RequestParam(value="id",required=true)String id) {
        return usuarioService.habilitarUsuario(id);
    }

    @GetMapping("/listAllUsuario")
    public List<UsuarioModel> listAllUsuario(){
        return usuarioService.listUsuarioTodas();
    }

    @GetMapping
    public UsuarioModel getUsuarioById(@RequestParam(value="id",required=true)String id){
        return usuarioService.getUsuarioById(id);
    }
    
    @PutMapping("/deshabilitarUsuario")
    public UsuarioModel deshabilitarUsuario(@RequestParam(value="id",required=true)String id) {
        return usuarioService.deshabilitarUsuario(id);
    }
    
    @GetMapping("/loguearUsuario")
    public UsuarioModel loguearUsuario(@RequestParam(value="username",required=true)String username,
    									@RequestParam(value="password",required=true)String password) {
    	return usuarioService.loguearUsuario(username, password);
    }
    
    
}
