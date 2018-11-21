package com.utn.meraki.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.utn.meraki.entity.TipoRubro;
import com.utn.meraki.model.RubroMasDemandadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Rubro;

import javax.persistence.NamedNativeQuery;

@Repository("rubroRepository")

public interface RubroRepository extends JpaRepository<Rubro, Serializable>{

	static final String RUBRO_MAS_DEMANDADOS_QUERY="SELECT DISTINCT r.nombre_rubro as 'nombreRubro',(SELECT COUNT(*) FROM solicitudes sol WHERE sol.id_rubro = r.id) AS 'cantidadSolicitudes' FROM solicitudes s \n" +
			"LEFT JOIN rubros r ON r.id=s.id_rubro\n" +
			"WHERE s.fecha_solicitud BETWEEN ?1 AND ?2 ORDER BY cantidadSolicitudes DESC LIMIT 4;";

	public Rubro findRubroById(String id);
	
	public Rubro findRubroByNombreRubro(String nombreRubro);

	public List<Rubro> findRubroByTipoRubro(TipoRubro tipoRubro);

	public Rubro findRubroByNombreRubroAndTipoRubro(String nombreRubro,TipoRubro tipoRubro);

	@Query(value = RUBRO_MAS_DEMANDADOS_QUERY,nativeQuery = true)
	List<Object[]> findRubrosMasDemandados(Date fechaDesde,Date fechahasta);

}
