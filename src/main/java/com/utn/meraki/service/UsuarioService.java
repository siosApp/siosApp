package com.utn.meraki.service;

import com.utn.meraki.entity.Usuario;
import com.utn.meraki.model.FiltroModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.model.UsuariosByRubro;
import com.utn.meraki.model.UsuariosRegistradosDestacadosModel;

import java.util.Date;
import java.util.List;

public interface UsuarioService {

    public abstract UsuarioModel crearUsuario(UsuarioModel rubroModel);

    public abstract UsuarioModel editarUsuario(UsuarioModel rubroModel);

    public abstract List<UsuarioModel> listUsuarioVigente();

    public abstract UsuarioModel habilitarUsuario(String id);

    public abstract List<UsuarioModel> listUsuarioTodas();

    public abstract UsuarioModel getUsuarioById(String id);

    public abstract UsuarioModel deshabilitarUsuario(String id);

    public abstract UsuarioModel loguearUsuario(String username, String password);

    public abstract UsuarioModel existeUsuario(String username);

    public abstract UsuarioModel existeMail(String mail);

    public abstract UsuarioModel validarMail(String mail);

    public abstract List<UsuarioDestacadoModel> filtrarUsuarios(FiltroModel filtroModel);

    public abstract UsuarioModel cambiarContrasena(String mail, String password, String codigo);

    public abstract UsuarioModel asignarOferente(UsuarioModel usuarioModel);

    public abstract UsuarioModel addRubro(String idUsuario, String nombreRubro, String nombreTipoRubro);

    public abstract UsuarioModel eliminarRubro(String idUsuario, String idUsuarioRubro);

    public abstract List<UsuariosByRubro> cantidadUsuariosByRubro();

    public abstract UsuarioModel getUsuarioByUsername(String username);
    
    public abstract UsuarioModel registrarUsuarioLogueado(String idUsuario);
    
    public abstract UsuarioModel registrarUsuarioDeslogueado(String idUsuario);
    
    public abstract Integer calcularCantidadUsuariosLinea();
    
    public abstract UsuariosRegistradosDestacadosModel cantidadUsuariosRegistradosDestacados(Date fechaDesde, Date fechaHasta);
    
    public abstract List<UsuariosRegistradosDestacadosModel> registradosDestacadosUltimosMeses();
    
    

}
