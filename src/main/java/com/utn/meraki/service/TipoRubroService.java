package com.utn.meraki.service;

import java.util.List;

import com.utn.meraki.model.TipoRubroModel;

public interface TipoRubroService {

	public abstract TipoRubroModel crearTipoRubro(TipoRubroModel tipoRubroModel);

	public abstract TipoRubroModel editarTipoRubro(TipoRubroModel tipoRubroModel);

	public abstract List<TipoRubroModel> listTipoRubroVigente();

	public abstract TipoRubroModel habilitarTipoRubro(String id);

	public abstract List<TipoRubroModel> listTipoRubroTodas();

	public abstract TipoRubroModel getTipoRubroById(String id);

	public abstract TipoRubroModel deshabilitarTipoRubro(String id);

}
