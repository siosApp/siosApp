package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.SolicitudEstado;

@Repository("solicitudEstadoRepository")

public interface SolicitudEstadoRepository extends JpaRepository<SolicitudEstado, Serializable>{
	
	public SolicitudEstado findSolicitudEstadoByFechaCambioEstado(Date fechaEstado);

}
