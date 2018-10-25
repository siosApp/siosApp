package com.utn.meraki.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.UsuarioDestacadoConverter;
import com.utn.meraki.entity.Destacado;
import com.utn.meraki.model.DestacadoModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.repository.DestacadoRepository;
import com.utn.meraki.repository.EstadoDestacadoRepository;
import com.utn.meraki.service.DestacadoService;

@Service("destacadoService")

public class DestacadoServiceImpl implements DestacadoService{

	//REPOSITORY
	@Autowired
	DestacadoRepository destacadoRepository;
	@Autowired
	EstadoDestacadoRepository estadoDestacadoRepository;
	
	//CONVERTER
	@Autowired
	UsuarioDestacadoConverter usuarioDestacadoConverter;
	
	@Override
	public List<UsuarioDestacadoModel> listUltimosDestacados() {
		List<UsuarioDestacadoModel> usuariosDestacados = new ArrayList<>();
		Date fechaActual = new Date(System.currentTimeMillis());
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaDestacado().getMonth()==fechaActual.getMonth()&&
					destacado.getFechaDestacado().getYear()==fechaActual.getYear()) {
				if(destacado.getEstadoDestacado().getNombreEstadoDestacado().equals("Destacado")) {
					if(destacado.getFechaVtoDestacado().after(fechaActual)) {
						destacado.setEstadoDestacado(estadoDestacadoRepository.findEstadoDestacadoByNombreEstadoDestacado("No destacado"));
						destacadoRepository.save(destacado);
					}else {
						UsuarioDestacadoModel usuarioDestacadoModel = usuarioDestacadoConverter.convertDestacadoToUsuarioDestacadoModel(destacado);
						usuariosDestacados.add(usuarioDestacadoModel);
					}
				}
			}
		}
		return usuariosDestacados;
	}

	@Override
	public DestacadoModel destacarPerfil(DestacadoModel destacadoModel) {
		Destacado destacado = usuarioDestacadoConverter.convertDestacadoModelToDestacado(destacadoModel);
		destacadoRepository.save(destacado);
		return usuarioDestacadoConverter.convertDestacadoToDestacadoModel(destacado);
	}

	
}
