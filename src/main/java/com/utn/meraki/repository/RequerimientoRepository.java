package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Requerimiento;
import com.utn.meraki.entity.Usuario;

@Repository("requerimientoRepository")

public interface RequerimientoRepository extends JpaRepository<Requerimiento, Serializable>{
	
	public Requerimiento findRequerimientoById(String id);
	
	public List<Requerimiento> findRequerimientoByUsuario(Usuario usuario);

}
