package com.utn.meraki.service;

import java.util.List;
import com.utn.meraki.model.DestacadoModel;
import com.utn.meraki.model.ListDestacadosModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.model.UsuarioModel;

public interface DestacadoService {
	
	public abstract List<UsuarioDestacadoModel> listUltimosDestacados();
	
	public abstract DestacadoModel destacarPerfil(DestacadoModel destacadoModel);
	
	public abstract ListDestacadosModel verCantidadDestacados();

	public abstract List<UsuarioModel> usuariosPorVencerDestacado();
	
    public abstract Integer cantidadUsuariosDestacados();

}
