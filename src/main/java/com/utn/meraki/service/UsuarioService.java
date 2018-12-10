package com.utn.meraki.service;

import com.utn.meraki.model.*;

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
    
    public abstract Integer cantidadUsuariosRegistrados();

    public abstract List<UsuarioModel> listUsuarioEnLinea();

    List<UsuarioModel> getUsuariosRegistradosFiltered(String sexo, Integer edadDesde, Integer edadHasta, String provincia, String departamento, String localidad, String tipoRubro, String rubro);

    List<UsuarioComentariosModel> getUsuarioConComentarios(String id);

    ReporteCalificacionDTO getUsuariosQueMeCalificaron(String idUsuario, boolean isDemandante, boolean isOferente);
}
