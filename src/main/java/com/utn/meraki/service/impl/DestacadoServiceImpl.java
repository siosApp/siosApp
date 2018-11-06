package com.utn.meraki.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.UsuarioConverter;
import com.utn.meraki.converter.UsuarioDestacadoConverter;
import com.utn.meraki.entity.Destacado;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.model.DestacadoModel;
import com.utn.meraki.model.ListDestacadosModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.model.UsuarioModel;
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
	@Autowired
	UsuarioConverter usuarioConverter;
	
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

	@Override
	public ListDestacadosModel verCantidadDestacados() {
		ListDestacadosModel listDestacadosModel = new ListDestacadosModel();
		Date fechaActual = new Date(System.currentTimeMillis());
		Integer cantidadDestacados = 0;
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaVtoDestacado().after(fechaActual)) {
				destacado.setEstadoDestacado(estadoDestacadoRepository.findEstadoDestacadoByNombreEstadoDestacado("No destacado"));
				destacadoRepository.save(destacado);
			}else {
				cantidadDestacados += 1;
				listDestacadosModel.getUsuariosDestacados().add(usuarioConverter.convertUsuarioToUsuarioModel(destacado.getUsuario()));
			}
		}
		listDestacadosModel.setCantidadDestacados(cantidadDestacados);
		return listDestacadosModel;
	}

	@Override
	public List<UsuarioModel> usuariosPorVencerDestacado() {
		List<UsuarioModel> usuarioModels = new ArrayList<>();
		for(Destacado destacado : destacadoRepository.findAll()) {
			Date fechaVto = destacado.getFechaVtoDestacado();
			Date fechaActual = new Date(System.currentTimeMillis());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaActual);
			calendar.add(calendar.WEEK_OF_MONTH, -1);
			Date fechaPorVencer = new Date(calendar.getTime().getTime());			
			if(destacado.getEstadoDestacado().getNombreEstadoDestacado().equals("Destacado")) {
				System.out.println("Fecha vto = " +fechaVto);
				System.out.println("Fecha por vencer = " +fechaPorVencer);
				if(fechaVto.after(fechaPorVencer)) {
					usuarioModels.add(usuarioConverter.convertUsuarioToUsuarioModel(destacado.getUsuario()));
				}
			}
		}
		return usuarioModels;
	}
	
	@Override
	public Integer cantidadUsuariosDestacados() {
		Integer cantidad = 0;
		if(destacadoRepository.findAll()!=null) {
			for(Destacado destacado : destacadoRepository.findAll()) {
				if(destacado.getEstadoDestacado().getNombreEstadoDestacado().equals("Destacado")) {
					cantidad += 1;
				}
			}
		}
		return cantidad;
	}

	@Override
	public List<DestacadoModel> verDestacadosByFechas(java.util.Date fechaDesde, java.util.Date fechaHasta) {
		List<DestacadoModel> destacados = new ArrayList<>();
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaDestacado().after(fechaDesde) && destacado.getFechaDestacado().before(fechaHasta)
					&& destacado.getEstadoDestacado().getNombreEstadoDestacado().equals("Destacado")) {
				destacados.add(usuarioDestacadoConverter.convertDestacadoToDestacadoModel(destacado));
			}
		}
		return destacados;
	}	
}
