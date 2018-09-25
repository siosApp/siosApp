package com.utn.meraki.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.OfertaRequerimiento;

@Repository("ofertaRequerimientoReposiory")

public interface OfertaRequerimientoRepository extends JpaRepository<OfertaRequerimiento, Serializable>{
	
	public OfertaRequerimiento findOfertaRequerimientoById(String idOfertaRequerimiento);

}
