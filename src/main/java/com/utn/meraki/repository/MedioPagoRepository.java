package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.Date;
import com.utn.meraki.entity.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.MedioPago;

@Repository("medioPagoRepository")

public interface MedioPagoRepository extends JpaRepository<MedioPago, Serializable>{
	
	public MedioPago findMedioPagoByNombreMedioPago(String nombreMedioPago);
	
	public MedioPago findMedioPagoById(String id);

}
