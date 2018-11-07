package com.utn.meraki.converter;

import com.utn.meraki.entity.Usuario;
import com.utn.meraki.entity.UsuarioRubro;
import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.model.UsuarioRubroModel;
import com.utn.meraki.repository.DomicilioRepository;
import com.utn.meraki.repository.TipoUsuarioRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.service.CalificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("usuarioConverter")
public class UsuarioConverter {

	//REPOSITORY
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    DomicilioRepository domicilioRepository;
    
    //CONVERTER
    @Autowired
    UsuarioRubroConverter usuarioRubroConverter;
    @Autowired
    DomicilioConverter domicilioConverter;
    
    //REPOSITORY
    @Autowired
    CalificacionService calificacionService;

    public Usuario convertUsuarioModelToUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = null;
        if(usuarioModel.getId()!=null) {
            usuario = usuarioRepository.findUsuarioById(usuarioModel.getId());
            usuario.setTipoUsuario(tipoUsuarioRepository.findByNombreTipoUsuario(usuarioModel.getTipoUsuario()));
        }else {
            usuario = new Usuario();
            usuario.setFechaRegistro(new Date(System.currentTimeMillis()));
            usuario.setTipoUsuario(tipoUsuarioRepository.findByNombreTipoUsuario("Modo Usuario"));
        }
        usuario.setNombre(usuarioModel.getNombre());
        usuario.setApellido(usuarioModel.getApellido());
        if(usuarioModel.getDomicilio()!=null){
            usuario.setDomicilio(domicilioConverter.convertDomicilioModelToDomicilio(usuarioModel.getDomicilio()));
        }
        usuario.setImagen(usuarioModel.getImagen());
        usuario.setFechaBaja(usuarioModel.getFechaBaja());
        usuario.setFechaNacimiento(usuarioModel.getFechaNacimiento());
        usuario.setFechaUltIngreso(usuarioModel.getFechaUltIngreso());
        usuario.setMail(usuarioModel.getMail());
        usuario.setOferente(usuarioModel.getOferente());
        usuario.setPassword(usuarioModel.getPassword());
        usuario.setSexo(usuarioModel.getSexo());
        usuario.setUsername(usuarioModel.getUsername());
        if(usuarioModel.getUsuarioRubros()!=null){
            usuario.setUsuarioRubros(getUsuariosRubros(usuarioModel.getUsuarioRubros()));
        }
        usuarioRepository.save(usuario);
        return usuario;
    }
    private List<UsuarioRubro> getUsuariosRubros(List<UsuarioRubroModel> usuarioRubrosModel){
        List<UsuarioRubro> usuarioRubros= new ArrayList<>();
        for(UsuarioRubroModel usuarioRubroModel: usuarioRubrosModel){
            usuarioRubros.add(usuarioRubroConverter.convertUsuarioRubroModelToUsuarioRubro(usuarioRubroModel));
        }
        return  usuarioRubros;
    }
    public UsuarioModel convertUsuarioToUsuarioModel(Usuario usuario) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuario.getId());
        usuarioModel.setFechaRegistro(usuario.getFechaRegistro());
        usuarioModel.setApellido(usuario.getApellido());
        usuarioModel.setUsername(usuario.getUsername());
        usuarioModel.setImagen(usuario.getImagen());
        usuarioModel.setSexo(usuario.getSexo());
        usuarioModel.setMail(usuario.getMail());
        usuarioModel.setNombre(usuario.getNombre());
        usuarioModel.setPassword(usuario.getPassword());
        usuarioModel.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioModel.setFechaUltIngreso(usuario.getFechaUltIngreso());
        usuarioModel.setFechaBaja(usuario.getFechaBaja());
        usuarioModel.setExperiencia(getExperienciaString(usuario));
        usuarioModel.setPromedioCalificacion(calificacionService.promedioCalificacionByUsuario(usuario.getId()));
        if(usuario.getOferente()!=null) {
        	usuarioModel.setOferente(usuario.getOferente());
        }
        if(usuario.getUsuarioRubros()!=null) {
        	for(UsuarioRubro usuarioRubro : usuario.getUsuarioRubros()) {
        		usuarioModel.getUsuarioRubros().add(usuarioRubroConverter.convertUsuarioRubroToUsuarioRubroModel(usuarioRubro));
        	}
        }
        if(usuario.getTipoUsuario()!=null) {
        	usuarioModel.setTipoUsuario(usuario.getTipoUsuario().getNombreTipoUsuario());
        }
        if(usuario.getDomicilio()!=null){
            usuarioModel.setDomicilio(domicilioConverter.convertDomicilioToDomicilioModel(usuario.getDomicilio()));
        }
        return usuarioModel;
    }
    public String getExperienciaString(Usuario usuario){
        if(usuario.getUsuarioRubros()!=null && !usuario.getUsuarioRubros().isEmpty() && !usuario.getUsuarioRubros().get(0).getExperiencias().isEmpty()){
            return usuario.getUsuarioRubros().get(0).getPrimerExperiencia().getDescripcion();
        }
        return "Sin experiencia previa";
    }
    public List<UsuarioRubroModel> getUsuariosRubrosModel(List<UsuarioRubro> usuarioRubros){
        List<UsuarioRubroModel> usuarioRubroModels= new ArrayList<>();
        for (UsuarioRubro usuarioRubro:usuarioRubros){
            usuarioRubroModels.add(usuarioRubroConverter.convertUsuarioRubroToUsuarioRubroModel(usuarioRubro));
        }
        return usuarioRubroModels;
    }
}
