package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Departamento;

@Repository("departamentoRepository")

public interface DepartamentoRepository extends JpaRepository<Departamento, Serializable>{
	
	public Departamento findDepartamentoByNombreDepartamento(String nombreDepartamento);
	
	public Departamento findDepartamentoById(String id);

}
