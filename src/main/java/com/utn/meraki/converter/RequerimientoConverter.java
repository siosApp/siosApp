package com.utn.meraki.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.repository.EstadoRequerimientoRepository;
import com.utn.meraki.repository.UsuarioRepository;

@Component("requerimientoConverter")

public class RequerimientoConverter {
	
	//REPOSITORY
	@Autowired
	EstadoRequerimientoRepository estadoRequerimientoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Requerimiento convertRequerimientoModelToRequerimiento(RequerimientoModel requerimientoModel) {
		Requerimiento requerimiento = new Requerimiento();
		requerimiento.setFechaPublicacion(new Date(System.currentTimeMillis()));
		requerimiento.setEstadoRequerimiento(estadoRequerimientoRepository.findEstadoRequerimientoByNombreEstado("Activo"));
		requerimiento.setDescripcion(requerimientoModel.getDescripcion());
		requerimiento.setPrecioApagar(requerimientoModel.getPrecioApagar());
		requerimiento.setTiempoEstimado(requerimientoModel.getTiempoEstimado());
		requerimiento.setTitulo(requerimientoModel.getTitulo());
		requerimiento.setUsuario(usuarioRepository.findUsuarioById(requerimientoModel.getIdUsuario()));
		//requerimiento.setArchivos(archivos);
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
		//requerimiento.getArchivos();
		return requerimientoModel;		
	}
	
	

}
