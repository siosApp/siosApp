package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.Date;//borrar
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.EstadoRequerimiento;

@Repository("EstadoRequerimientoRepository")

public interface EstadoRequerimientoRepository extends JpaRepository<EstadoRequerimiento, Serializable> {
	
	public EstadoRequerimiento findEstadoRequerimientoByNombreEstado(String nombreEstado);
	
	public EstadoRequerimiento findEstadoRequerimientoById(String id);
}
