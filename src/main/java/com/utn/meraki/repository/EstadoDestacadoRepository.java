package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.Date;
import com.utn.meraki.entity.EstadoDestacado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.EstadoDestacado;

@Repository("estadoDestacadoRepository")

public interface EstadoDestacadoRepository extends JpaRepository<EstadoDestacado, Serializable>{
	
	public EstadoDestacado findEstadoDestacadoByNombreEstadoDestacado(String nombreEstadoDestacado);
	
	public EstadoDestacado findEstadoDestacadoById(String id);

}