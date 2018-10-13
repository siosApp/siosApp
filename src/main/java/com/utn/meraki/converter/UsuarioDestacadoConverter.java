package com.utn.meraki.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.Destacado;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.entity.UsuarioRubro;
import com.utn.meraki.model.DestacadoModel;
import com.utn.meraki.model.RubroModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.repository.EstadoDestacadoRepository;
import com.utn.meraki.repository.MedioPagoRepository;
import com.utn.meraki.repository.TarifaRepository;
import com.utn.meraki.repository.UsuarioRepository;

@Component("usuarioDestacadoConverter")

public class UsuarioDestacadoConverter {
	
	//REPOSITORY
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EstadoDestacadoRepository estadoDestacadoRepository;
	@Autowired
	TarifaRepository tarifaRepository;
	@Autowired
	MedioPagoRepository medioPagoRepository;
	
	//CONVERTER
	@Autowired
	RubroConverter rubroConverter;
	@Autowired
	DomicilioConverter domicilioConverter;
	
	public UsuarioDestacadoModel convertDestacadoToUsuarioDestacadoModel(Destacado destacado) {
		UsuarioDestacadoModel usuarioDestacadoModel = new UsuarioDestacadoModel();
		usuarioDestacadoModel.setIdUsuario(destacado.getUsuario().getId());
		usuarioDestacadoModel.setNombre(destacado.getUsuario().getNombre());
		usuarioDestacadoModel.setApellido(destacado.getUsuario().getApellido());
		usuarioDestacadoModel.setExperiencia(getExperienciaString(destacado.getUsuario()));
		usuarioDestacadoModel.setDomicilio(domicilioConverter.convertDomicilioToDomicilioModel(destacado.getUsuario().getDomicilio()));
		for(UsuarioRubro usuarioRubro : usuarioRepository.findUsuarioById(destacado.getUsuario().getId()).getUsuarioRubros()) {
			RubroModel rubroModel = rubroConverter.convertRubroToRubroModel(usuarioRubro.getRubro());
			usuarioDestacadoModel.getRubros().add(rubroModel);
		}
		return usuarioDestacadoModel;
	}
	public String getExperienciaString(Usuario usuario){
		if(!usuario.getUsuarioRubros().get(0).getExperiencias().isEmpty()){
			return usuario.getUsuarioRubros().get(0).getPrimerExperiencia().getDescripcion();
		}
		return "Sin experiencia previa";
	}
	public UsuarioDestacadoModel convertUsuarioToUsuarioDestacadoModel(Usuario usuario) {
		UsuarioDestacadoModel usuarioDestacadoModel = new UsuarioDestacadoModel();
		usuarioDestacadoModel.setIdUsuario(usuario.getId());
		usuarioDestacadoModel.setNombre(usuario.getNombre());
		usuarioDestacadoModel.setApellido(usuario.getApellido());
		usuarioDestacadoModel.setExperiencia(getExperienciaString(usuario));
		usuarioDestacadoModel.setImagen(usuario.getImagen());
		usuarioDestacadoModel.setDomicilio(domicilioConverter.convertDomicilioToDomicilioModel(usuario.getDomicilio()));
		for(UsuarioRubro usuarioRubro : usuarioRepository.findUsuarioById(usuario.getId()).getUsuarioRubros()) {
			RubroModel rubroModel = rubroConverter.convertRubroToRubroModel(usuarioRubro.getRubro());
			usuarioDestacadoModel.getRubros().add(rubroModel);
		}
		return usuarioDestacadoModel;
	}
	
	public Destacado convertDestacadoModelToDestacado(DestacadoModel destacadoModel) {
		Destacado destacado = new Destacado();
		destacado.setFechaDestacado(new Date(System.currentTimeMillis()));
		destacado.setEstadoDestacado(estadoDestacadoRepository.findEstadoDestacadoByNombreEstadoDestacado("Vigente"));
		destacado.setMedioPago(medioPagoRepository.findMedioPagoByNombreMedioPago(destacadoModel.getNombreMedioPago()));
		destacado.setMonto(destacadoModel.getMonto());
		destacado.setUsuario(usuarioRepository.findUsuarioByUsername(destacadoModel.getNombreUsuario()));
		return destacado;
	}
	
	public DestacadoModel convertDestacadoToDestacadoModel(Destacado destacado) {
		DestacadoModel destacadoModel = new DestacadoModel();
		destacadoModel.setId(destacado.getId());
		destacadoModel.setFechaDestacado(destacado.getFechaDestacado());
		destacadoModel.setMonto(destacado.getMonto());
		destacadoModel.setNombreEstado(destacado.getEstadoDestacado().getNombreEstadoDestacado());
		destacadoModel.setNombreMedioPago(destacado.getMedioPago().getNombreMedioPago());
		destacadoModel.setNombreUsuario(destacado.getUsuario().getUsername());
		return destacadoModel;
	}
	
	
}
