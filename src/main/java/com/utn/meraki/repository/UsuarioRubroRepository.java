package com.utn.meraki.repository;

import com.utn.meraki.entity.Rubro;
import com.utn.meraki.entity.UsuarioRubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("usuarioRubroRepository")

public interface UsuarioRubroRepository extends JpaRepository<UsuarioRubro,Serializable> {
   
	public UsuarioRubro findById(String id);
	
    public List<UsuarioRubro> findByRubro(Rubro rubro);

}
