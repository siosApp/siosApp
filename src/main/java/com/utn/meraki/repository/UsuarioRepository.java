package com.utn.meraki.repository;


import com.utn.meraki.entity.Usuario;
import com.utn.meraki.entity.UsuarioRubro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario,Serializable> {

    public Usuario findUsuarioById(String id);

    public Usuario findUsuarioByTipoUsuario(String tipoUsuario);
    
    public Usuario findUsuarioByusernameAndPassword(String username, String password);

    public Usuario findUsuarioByUsername(String username);

    public Usuario findUsuarioByMail(String mail);

    public Usuario findUsuarioByUsuarioRubros(UsuarioRubro UsuarioRubro);

    public List<Usuario> findByLogueado(boolean isLogueado);

}
