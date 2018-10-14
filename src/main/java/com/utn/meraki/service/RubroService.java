package com.utn.meraki.service;

import java.sql.Date;
import java.util.List;

import com.utn.meraki.model.CertificadoModel;
import com.utn.meraki.model.ExperienciaModel;
import com.utn.meraki.model.RubroMasDemandadoModel;
import com.utn.meraki.model.RubroModel;
import com.utn.meraki.model.UsuarioRubroModel;

public interface RubroService {

	public abstract RubroModel crearRubro(RubroModel rubroModel);

	public abstract RubroModel editarRubro(RubroModel rubroModel);

	public abstract List<RubroModel> listRubroVigente();

	public abstract RubroModel habilitarRubro(String id);

	public abstract List<RubroModel> listRubroTodas();

	public abstract RubroModel getRubroById(String id);

	public abstract RubroModel deshabilitarRubro(String id);

	public abstract List<RubroModel> listRubroVigenteByTipoRubro(String tipoRubro);
	
	public abstract RubroModel getRubroByNombreRubro(String nombreRubro);

	public abstract UsuarioRubroModel anadirOrEliminarCertificado(String idUsuarioRubro, CertificadoModel certificadoModel);

	public abstract UsuarioRubroModel anadirOrEliminarExperiencia(String idUsuarioRubro, ExperienciaModel experienciaModel);
	
	public abstract List<RubroMasDemandadoModel> rubrosMasDemandados(Date fechaDesde, Date fechaHasta);
	
}
