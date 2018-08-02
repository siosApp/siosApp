package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.TipoRubro;

@Repository("tipoRubroRepository")

public interface TipoRubroRepository extends JpaRepository<TipoRubro, Serializable>{
	
	public TipoRubro findTipoRubroByNombreTipoRubro(String nombreTipoRubro);
	
	public TipoRubro findTipoRubroById(String id);

}
