package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.List;

import com.utn.meraki.entity.TipoRubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Rubro;

@Repository("rubroRepository")

public interface RubroRepository extends JpaRepository<Rubro, Serializable>{
	
	public Rubro findRubroById(String id);
	
	public Rubro findRubroByNombreRubro(String nombreRubro);

	public List<Rubro> findRubroByTipoRubro(TipoRubro tipoRubro);

}
