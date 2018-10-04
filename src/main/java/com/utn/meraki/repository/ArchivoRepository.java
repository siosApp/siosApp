package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Archivo;

@Repository("archivoRepository")

public interface ArchivoRepository extends JpaRepository<Archivo, Serializable>{

}
