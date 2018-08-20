package com.utn.meraki.converter;

import com.utn.meraki.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utn.meraki.entity.TipoUsuario;
import com.utn.meraki.model.TipoUsuarioModel;

@Component("tipoUsuarioConverter")
public class TipoUsuarioConverter {

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	//CONVERTER
	//Me muestra un tipo de usuario en pantalla
	public TipoUsuarioModel convertTipoUsuarioToTipoUsuarioModel(TipoUsuario tipoUsuario) {
		TipoUsuarioModel tipoUsuarioModel = new TipoUsuarioModel();
		tipoUsuarioModel.setNombreTipoUsuario(tipoUsuario.getNombreTipoUsuario());
		tipoUsuarioModel.setDescripcion(tipoUsuario.getDescripcion());
		tipoUsuarioModel.setFechaBaja(tipoUsuario.getFechaBaja());
		tipoUsuarioModel.setId(tipoUsuario.getId());
		return tipoUsuarioModel;
	}

	public TipoUsuario convertTipoUsuarioModelToTipoUsuario(TipoUsuarioModel tipoUsuarioModel) {
		TipoUsuario tipoUsuario = null;
		if(tipoUsuarioModel.getId()!=null) {
			tipoUsuario = tipoUsuarioRepository.findTipoUsuarioById(tipoUsuarioModel.getId());
		}else {
			tipoUsuario = new TipoUsuario();
		}
		tipoUsuario.setNombreTipoUsuario(tipoUsuarioModel.getNombreTipoUsuario());
		tipoUsuario.setFechaBaja(tipoUsuarioModel.getFechaBaja());
		tipoUsuarioRepository.save(tipoUsuario);
		return tipoUsuario;
	}
}
