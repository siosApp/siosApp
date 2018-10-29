package com.utn.meraki.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Archivo;
import com.utn.meraki.entity.OfertaRequerimiento;
import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.repository.ArchivoRepository;
import com.utn.meraki.repository.EstadoRequerimientoRepository;
import com.utn.meraki.repository.OfertaRequerimientoRepository;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.repository.UsuarioRepository;

@Component("requerimientoConverter")

public class RequerimientoConverter {
	
	//REPOSITORY
	@Autowired
	EstadoRequerimientoRepository estadoRequerimientoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ArchivoRepository archivoRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	OfertaRequerimientoRepository ofertaRequerimientoRepository;
	@Autowired
	OfertaRequerimientoConverter ofertaRequerimientoConverter;
	
	public Requerimiento convertRequerimientoModelToRequerimiento(RequerimientoModel requerimientoModel) {
		Requerimiento requerimiento = new Requerimiento();
		requerimiento.setFechaPublicacion(new Date(System.currentTimeMillis()));
		requerimiento.setEstadoRequerimiento(estadoRequerimientoRepository.findEstadoRequerimientoByNombreEstado("Activo"));
		requerimiento.setDescripcion(requerimientoModel.getDescripcion());
		requerimiento.setPrecioApagar(requerimientoModel.getPrecioApagar());
		requerimiento.setTiempoEstimado(requerimientoModel.getTiempoEstimado());
		requerimiento.setTitulo(requerimientoModel.getTitulo());
		requerimiento.setUsuario(usuarioRepository.findUsuarioById(requerimientoModel.getIdUsuario()));
		requerimiento.setRubro(rubroRepository.findRubroByNombreRubro(requerimientoModel.getNombreRubro()));
		for(String urlArchivo : requerimientoModel.getUrlArchivos()) {
			Archivo archivo = new Archivo(urlArchivo);
			archivoRepository.save(archivo);
			requerimiento.getArchivos().add(archivo);
		}
		return requerimiento;
	}
	
	public RequerimientoModel convertRequerimientoToRequerimientoModel(Requerimiento requerimiento) {
		RequerimientoModel requerimientoModel = new RequerimientoModel();
		requerimientoModel.setId(requerimiento.getId());
		requerimientoModel.setDescripcion(requerimiento.getDescripcion());
		requerimientoModel.setFechaPublicacion(requerimiento.getFechaPublicacion());
		requerimientoModel.setPrecioApagar(requerimiento.getPrecioApagar());
		requerimientoModel.setTitulo(requerimiento.getTitulo());
		requerimientoModel.setTiempoEstimado(requerimiento.getTiempoEstimado());
		requerimientoModel.setIdUsuario(requerimiento.getUsuario().getId());
		requerimientoModel.setNombreEstadoRequerimiento(requerimiento.getEstadoRequerimiento().getNombreEstado());
		requerimientoModel.setNombreRubro(requerimiento.getRubro().getNombreRubro());
		if(ofertaRequerimientoRepository.findOfertaRequerimientoByRequerimiento(requerimiento)!=null) {
			Integer cantidadOfertas = 0;
			for(OfertaRequerimiento oferta : ofertaRequerimientoRepository.findOfertaRequerimientoByRequerimiento(requerimiento)) {
				requerimientoModel.getOfertas().add(ofertaRequerimientoConverter.convertOfertaRequerimientoToOfertaRequerimientoModel(oferta));
				cantidadOfertas += 1;
			}
			requerimientoModel.setCantidadOfertas(cantidadOfertas);
		}else {
			requerimientoModel.setCantidadOfertas(0);
		}
		for(Archivo archivo : requerimiento.getArchivos()) {
			requerimientoModel.getUrlArchivos().add(archivo.getUrlArchivo());
		}
		return requerimientoModel;		
	}
	
	

}
