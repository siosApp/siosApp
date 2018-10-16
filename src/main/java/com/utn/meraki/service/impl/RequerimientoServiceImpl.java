package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.meraki.converter.OfertaRequerimientoConverter;
import com.utn.meraki.converter.RequerimientoConverter;
import com.utn.meraki.converter.SolicitudConverter;
import com.utn.meraki.entity.OfertaRequerimiento;
import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.model.OfertaRequerimientoModel;
import com.utn.meraki.model.RequerimientoModel;
import com.utn.meraki.model.SolicitudModel;
import com.utn.meraki.repository.OfertaRequerimientoRepository;
import com.utn.meraki.repository.RequerimientoRepository;
import com.utn.meraki.repository.SolicitudRepository;
import com.utn.meraki.service.RequerimientoService;

@Service("requerimientoService")

public class RequerimientoServiceImpl implements RequerimientoService{

	//REPOSITORY
	@Autowired
	RequerimientoRepository requerimientoRepository;
	@Autowired
	SolicitudRepository solicitudRepository;
	@Autowired
	OfertaRequerimientoRepository ofertaRequerimientoRepository;
	
	//CONVERTER
	@Autowired
	RequerimientoConverter requerimientoConverter;
	@Autowired
	SolicitudConverter solicitudConverter;
	@Autowired
	OfertaRequerimientoConverter ofertaRequerimientoConverter;
	
	//METODOS
	@Override
	public RequerimientoModel publicarRequerimiento(RequerimientoModel requerimientoModel) {
		Requerimiento requerimiento = requerimientoConverter.convertRequerimientoModelToRequerimiento(requerimientoModel);
		requerimientoRepository.save(requerimiento);
		return requerimientoModel;
	}

	@Override
	public OfertaRequerimientoModel ofertarRequerimiento(OfertaRequerimientoModel ofertaRequerimientoModel) {
		OfertaRequerimiento ofertaRequerimiento = ofertaRequerimientoConverter.
				convertOfertaRequerimientoModelToOfertaRequerimiento(ofertaRequerimientoModel);
		ofertaRequerimientoRepository.save(ofertaRequerimiento);
		return ofertaRequerimientoConverter.convertOfertaRequerimientoToOfertaRequerimientoModel(ofertaRequerimiento);
	}

	@Override
	public List<RequerimientoModel> getRequerimientosActivos() {
		List<RequerimientoModel> listRequerimientos = new ArrayList<>();
		for(Requerimiento requerimiento : requerimientoRepository.findAll()) {
			if(requerimiento.getEstadoRequerimiento().getNombreEstado().equals("Activo")) {
				listRequerimientos.add(requerimientoConverter.convertRequerimientoToRequerimientoModel(requerimiento));
			}
		}
		return listRequerimientos;
	}

	@Override
	public RequerimientoModel findRequerimientoById(String idRequerimiento) {
		Requerimiento requerimiento = requerimientoRepository.findRequerimientoById(idRequerimiento);
		return requerimientoConverter.convertRequerimientoToRequerimientoModel(requerimiento);
	}

	@Override
	public SolicitudModel aceptarOfertaRequerimiento(String idOfertaRequerimiento) {
		OfertaRequerimiento ofertaRequerimiento = ofertaRequerimientoRepository.findOfertaRequerimientoById(idOfertaRequerimiento);
		ofertaRequerimiento.setAsignado(true);
		ofertaRequerimientoRepository.save(ofertaRequerimiento);
		Solicitud solicitud = solicitudConverter.convertOfertaRequerimientoToSolicitud(ofertaRequerimiento);
		solicitudRepository.save(solicitud);
		return solicitudConverter.convertSolicitudToSolicitudModel(solicitud);
	}

}
