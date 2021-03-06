package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Rubro;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.Usuario;

@Repository("solicitudRepository")

public interface SolicitudRepository extends JpaRepository<Solicitud, Serializable>{
	
	public Solicitud findSolicitudById(String idSolicitud);
	
	public List<Solicitud> findSolicitudByUsuarioOferente(Usuario usuario);
	
	public List<Solicitud> findSolicitudByRubro(Rubro rubro);


	public List<Solicitud> findSolicitudByUsuarioDemandante(Usuario usuario);

}
