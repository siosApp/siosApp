package com.utn.meraki.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utn.meraki.entity.Certificado;
import com.utn.meraki.model.CertificadoModel;
import com.utn.meraki.repository.CertificadoRepository;

@Component("certificadoConverter")

public class CertificadoConverter {
	
	//REPOSITORY
	@Autowired
	CertificadoRepository certificadoRepository;
	
	//CONVERTERS
	//Me crea o edita un certificado
	public Certificado convertCertificadoModelToCertificado(CertificadoModel certificadoModel) {
		Certificado certificado = null;
		if(certificadoModel.getId()!=null) {
			certificado = certificadoRepository.findCertificadoById(certificadoModel.getId());
		}else {
			certificado = new Certificado();
		}
		certificado.setNombreCertificado(certificadoModel.getNombreCertificado());
		certificado.setFechaCertificado(certificadoModel.getFechaCertificado());
		certificadoRepository.save(certificado);
		return certificado;
	}
	
	//Me muestra un certificado en pantalla
	public CertificadoModel convertCertificadoToCertificadoModel(Certificado certificado) {
		CertificadoModel certificadoModel = new CertificadoModel();
		certificadoModel.setId(certificado.getId());
		certificadoModel.setNombreCertificado(certificado.getNombreCertificado());
		certificadoModel.setFechaCertificado(certificado.getFechaCertificado());
		return certificadoModel;
	}

}
