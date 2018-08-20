package com.utn.meraki.repository;

import com.utn.meraki.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("tipoUsuarioRepository")
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Serializable> {
    TipoUsuario findTipoUsuarioById(String id);
}
