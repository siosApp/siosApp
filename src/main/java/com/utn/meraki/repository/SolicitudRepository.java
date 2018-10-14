package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Solicitud;

@Repository("solicitudRepository")

public interface SolicitudRepository extends JpaRepository<Solicitud, Serializable>{
	
	public Solicitud findSolicitudById(String idSolicitud);
	
	public List<Solicitud> findSolicitudByUsuarioOferente(String idUsuarioOferente);

}
