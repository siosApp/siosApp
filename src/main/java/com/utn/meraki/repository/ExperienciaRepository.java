package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Experiencia;

@Repository("experienciaRepository")

public interface ExperienciaRepository extends JpaRepository<Experiencia, Serializable>{
	
	public Experiencia findExperienciaById(String id);
	
	public Experiencia findExperienciaByDescripcion(String descripcion);

}
