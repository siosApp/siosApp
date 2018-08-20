package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.Date;
import com.utn.meraki.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Provincia;

@Repository("provinciaRepository")

public interface ProvinciaRepository extends JpaRepository<Provincia, Serializable>{
	
	public Provincia findProvinciaByNombreProvincia(String nombreProvincia);
	
	public Provincia findProvinciaById(String id);

}
