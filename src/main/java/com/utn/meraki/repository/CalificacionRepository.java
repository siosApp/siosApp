package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Calificacion;
import com.utn.meraki.entity.Solicitud;
import com.utn.meraki.entity.Usuario;

@Repository("calificacionRepository")

public interface CalificacionRepository extends JpaRepository<Calificacion, Serializable>{
	
	public List<Calificacion> findCalificacionBySolicitud(Solicitud solicitud);
	
	public Calificacion findCalificacionBySolicitudAndUsuario(Solicitud solicitud, Usuario usuario);

}
