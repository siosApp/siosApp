package com.utn.meraki.repository;

import com.utn.meraki.entity.Rubro;
import com.utn.meraki.entity.UsuarioRubro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UsuarioRubroRepository extends JpaRepository<UsuarioRubro,Serializable> {
    public UsuarioRubro findById(String id);
    public UsuarioRubro findByRubro(Rubro rubro);

}
