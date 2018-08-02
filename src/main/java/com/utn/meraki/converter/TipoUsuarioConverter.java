package com.utn.meraki.converter;

import org.springframework.stereotype.Component;
import com.utn.meraki.entity.TipoUsuario;
import com.utn.meraki.model.TipoUsuarioModel;

@Component("tipoUsuarioConverter")

public class TipoUsuarioConverter {
	
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

}
