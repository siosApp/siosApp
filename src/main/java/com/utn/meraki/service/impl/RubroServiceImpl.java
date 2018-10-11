package com.utn.meraki.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.meraki.converter.CertificadoConverter;
import com.utn.meraki.converter.ExperienciaConverter;
import com.utn.meraki.converter.RubroConverter;
import com.utn.meraki.converter.UsuarioRubroConverter;
import com.utn.meraki.entity.*;
import com.utn.meraki.model.CertificadoModel;
import com.utn.meraki.model.ExperienciaModel;
import com.utn.meraki.model.UsuarioRubroModel;
import com.utn.meraki.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.meraki.model.RubroModel;
import com.utn.meraki.service.RubroService;

@Service("rubroService")

public class RubroServiceImpl implements RubroService{

	@Autowired
	private RubroConverter rubroConverter;
	@Autowired
	private RubroRepository rubroRepository;
	@Autowired
	private TipoRubroRepository tipoRubroRepository;
	@Autowired
	private UsuarioRubroRepository usuarioRubroRepository;
	@Autowired
	private CertificadoConverter certificadoConverter;
	@Autowired
	private UsuarioRubroConverter usuarioRubroConverter;
	@Autowired
	private CertificadoRepository certificadoRepository;
	@Autowired
	private ExperienciaRepository experienciaRepository;
	@Autowired
	private ExperienciaConverter experienciaConverter;

	@Override
	public RubroModel crearRubro(RubroModel rubroModel) {
		Rubro Rubro = rubroConverter.convertRubroModelToRubro(rubroModel);
		return rubroConverter.convertRubroToRubroModel(Rubro);
	}

	@Override
	public RubroModel editarRubro(RubroModel rubroModel) {
		Rubro rubro = rubroConverter.convertRubroModelToRubro(rubroModel);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override
	public List<RubroModel> listRubroVigente() {
		List<RubroModel> listRubros = new ArrayList<>();
		for(Rubro rubro : rubroRepository.findAll()) {
			if(rubro.getFechaBaja()==null) {
				listRubros.add(rubroConverter.convertRubroToRubroModel(rubro));
			}
		}
		return listRubros;
	}

	@Override
	public RubroModel habilitarRubro(String id) {
		Rubro rubro = rubroRepository.findRubroById(id);
		rubro.setFechaBaja(null);
		rubroRepository.save(rubro);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override
	public List<RubroModel> listRubroTodas() {
		List<RubroModel> listRubros = new ArrayList<>();
		for(Rubro rubro : rubroRepository.findAll()) {
			listRubros.add(rubroConverter.convertRubroToRubroModel(rubro));
		}
		return listRubros;
	}

	@Override
	public RubroModel getRubroById(String id) {
		if(id !=null){
			return rubroConverter.convertRubroToRubroModel(rubroRepository.findRubroById(id));
		}
		return new RubroModel();
	}

	@Override
	public RubroModel deshabilitarRubro(String id) {
		Rubro Rubro = rubroRepository.findRubroById(id);
		Rubro.setFechaBaja(new java.util.Date());
		rubroRepository.save(Rubro);
		return rubroConverter.convertRubroToRubroModel(Rubro);
	}

	@Override
	public List<RubroModel> listRubroVigenteByTipoRubro(String tipoRubro) {
		TipoRubro tipoRubroEntity = tipoRubroRepository.findTipoRubroByNombreTipoRubro(tipoRubro);
		List<Rubro> rubros=rubroRepository.findRubroByTipoRubro(tipoRubroEntity);
		List<RubroModel> models= new ArrayList<>();
		for(Rubro rubro: rubros){
			if(rubro.getFechaBaja()==null){
				models.add(rubroConverter.convertRubroToRubroModel(rubro));
			}
		}
		return models;
	}

	@Override
	public RubroModel getRubroByNombreRubro(String nombreRubro) {
		Rubro rubro = rubroRepository.findRubroByNombreRubro(nombreRubro);
		return rubroConverter.convertRubroToRubroModel(rubro);
	}

	@Override
	public UsuarioRubroModel anadirOrEliminarCertificado(String idUsuarioRubro, CertificadoModel certificadoModel) {
		UsuarioRubro usuarioRubro=usuarioRubroRepository.findById(idUsuarioRubro);
		if(certificadoModel.getId()!=null){
			Certificado certificado=certificadoRepository.findCertificadoById(certificadoModel.getId());
			usuarioRubro.eliminarCertificado(certificado);
			certificadoRepository.delete(certificado);
		}
		else{
			usuarioRubro.addCertificado(certificadoConverter.convertCertificadoModelToCertificado(certificadoModel));
		}
		usuarioRubroRepository.save(usuarioRubro);
		return usuarioRubroConverter.convertUsuarioRubroToUsuarioRubroModel(usuarioRubro);
	}

	@Override
	public UsuarioRubroModel anadirOrEliminarExperiencia(String idUsuarioRubro, ExperienciaModel experienciaModel) {
		UsuarioRubro usuarioRubro=usuarioRubroRepository.findById(idUsuarioRubro);
		if(experienciaModel.getId()!=null){
			Experiencia experiencia=experienciaRepository.findExperienciaById(experienciaModel.getId());
			usuarioRubro.eliminarExperiencia(experiencia);
			experienciaRepository.delete(experiencia);
		}
		else{
			usuarioRubro.addExperiencia(experienciaConverter.convertExperienciaModelToExperiencia(experienciaModel));
		}
		usuarioRubroRepository.save(usuarioRubro);
		return usuarioRubroConverter.convertUsuarioRubroToUsuarioRubroModel(usuarioRubro);
	}
}
