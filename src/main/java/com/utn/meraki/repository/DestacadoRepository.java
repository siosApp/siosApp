package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Destacado;
import com.utn.meraki.entity.Usuario;

@Repository("destacadoRepository")

public interface DestacadoRepository extends JpaRepository<Destacado, Serializable>{
	
	public Destacado findDestacadoByUsuario(Usuario usuario);

}
