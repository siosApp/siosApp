package com.utn.meraki.repository;


import com.utn.meraki.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario,Serializable> {

    public Usuario findUsuarioById(String id);

    public Usuario findUsuarioByTipoUsuario(String tipoUsuario);
    
    public Usuario findUsuarioByusernameAndPassword(String username, String password);

}
