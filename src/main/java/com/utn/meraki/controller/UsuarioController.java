package com.utn.meraki.controller;

import com.utn.meraki.entity.Usuario;
import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.model.UsuariosByRubro;
import com.utn.meraki.model.UsuariosRegistradosDestacadosModel;
import com.utn.meraki.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    
    @GetMapping("/existeUsuario")
    public UsuarioModel existeUsuario(@RequestParam(value="username",required=true)String username){
        return usuarioService.existeUsuario(username);
    }
    @GetMapping("/existeMail")
    public UsuarioModel existeMail(@RequestParam(value="mail",required=true)String mail){
        return usuarioService.existeMail(mail);
    }
    @GetMapping("/validarMail")
    public UsuarioModel validarMail(@RequestParam(value="mail",required=true)String mail){
        return usuarioService.validarMail(mail);
    }
    
    @PostMapping("/change")
    public UsuarioModel validarMail(@RequestParam(value="mail",required=true)String mail,
                                    @RequestParam(value="password",required=true)String password,
                                    @RequestParam(value="codigo",required=true)String codigo){
        return usuarioService.cambiarContrasena(mail,password,codigo);
    }
    
    @PostMapping("/asignarOferente")
    public UsuarioModel asignarOferente(@RequestBody UsuarioModel usuarioModel) {
    	return usuarioService.asignarOferente(usuarioModel);
    }

    @PostMapping("/addRubro")
    public UsuarioModel añadirRubro(@RequestParam(value="idUsuario",required=true)String idUsuario,
                                    @RequestParam(value="rubro",required=true)String rubro,
                                    @RequestParam(value="tipoRubro",required=true)String tipoRubro) {
        return usuarioService.addRubro(idUsuario,rubro,tipoRubro);
    }
    @PostMapping("/deleteRubro")
    public UsuarioModel deleteRubro(@RequestParam(value="idUsuario",required=true)String idUsuario,
                                    @RequestParam(value="usuarioRubro",required=true)String usuarioRubro) {
        return usuarioService.eliminarRubro(idUsuario,usuarioRubro);

    }
    @GetMapping("/cantidadUsuariosByRubro")
    public List<UsuariosByRubro> cantidadUsuariosByRubro(){
    	return usuarioService.cantidadUsuariosByRubro();
    }

    @GetMapping("/username")
    public UsuarioModel getUsuarioByUsername(@RequestParam(value = "username") String username){
        return usuarioService.getUsuarioByUsername(username);
    }
    
    @PutMapping("/registrarUsuarioLogueado")
    public UsuarioModel registrarUsuarioLogueado(@RequestParam(value="idUsuario",required=true)String idUsuario) {
    	return usuarioService.registrarUsuarioLogueado(idUsuario);
    }
    
    @PutMapping("/registrarUsuarioDeslogueado")
    public UsuarioModel registrarUsuarioDeslogueado(@RequestParam(value="idUsuario",required=true)String idUsuario) {
    	return usuarioService.registrarUsuarioDeslogueado(idUsuario);
    }
    
    @GetMapping("/calcularCantidadUsuariosLinea")
    public Integer calcularCantidadUsuariosLinea() {
    	return usuarioService.calcularCantidadUsuariosLinea();
    }
    
    @GetMapping("/cantidadUsuariosRegistradosDestacados")
    public UsuariosRegistradosDestacadosModel cantidadUsuariosRegistradosDestacados(
    		@RequestParam(value="fechaDesde",required=true)Date fechaDesde,@RequestParam(value="fechaHasta",required=true)Date fechaHasta) {
    	return usuarioService.cantidadUsuariosRegistradosDestacados(fechaDesde, fechaHasta);
    }
    
    @GetMapping("/registradosDestacadosUltimosMeses")
    public List<UsuariosRegistradosDestacadosModel> registradosDestacadosUltimosMeses(){
    	return usuarioService.registradosDestacadosUltimosMeses();
    }
    
    @GetMapping("/cantidadUsuariosRegistrados")
    public Integer cantidadUsuariosRegistrados() {
    	return usuarioService.cantidadUsuariosRegistrados();
    }
}
