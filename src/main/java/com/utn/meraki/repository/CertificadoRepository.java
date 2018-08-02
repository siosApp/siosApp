package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utn.meraki.entity.Certificado;

@Repository("certificadoRepository")

public interface CertificadoRepository extends JpaRepository<Certificado, Serializable>{
	
	public Certificado findCertificadoById(String id);
	
	public Certificado findCertificadoByNombreCertificado(String nombreCertificado);
	
}
