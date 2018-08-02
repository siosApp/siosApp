package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Domicilio;

@Repository("domicilioRepository")

public interface DomicilioRepository extends JpaRepository<Domicilio, Serializable>{
	
	public abstract Domicilio findDomicilioById(String id);

}
