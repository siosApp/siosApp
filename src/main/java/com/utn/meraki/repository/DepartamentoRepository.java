package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;

import com.utn.meraki.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Departamento;

@Repository("departamentoRepository")

public interface DepartamentoRepository extends JpaRepository<Departamento, Serializable>{
	
	public Departamento findDepartamentoByNombreDepartamento(String nombreDepartamento);
	
	public Departamento findDepartamentoById(String id);

	public Departamento findDepartamentoByNombreDepartamentoAndProvincia(String nombreDepartamento,Provincia provincia);

	public List<Departamento> findDepartamentoByProvincia(Provincia provincia);

}
