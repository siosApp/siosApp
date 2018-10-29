package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.OfertaRequerimiento;
import com.utn.meraki.entity.Requerimiento;

@Repository("ofertaRequerimientoReposiory")

public interface OfertaRequerimientoRepository extends JpaRepository<OfertaRequerimiento, Serializable>{
	
	public OfertaRequerimiento findOfertaRequerimientoById(String idOfertaRequerimiento);
	
	public List<OfertaRequerimiento> findOfertaRequerimientoByRequerimiento(Requerimiento requerimiento);

}
