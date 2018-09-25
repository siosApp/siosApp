package com.utn.meraki.converter;

import com.utn.meraki.entity.Certificado;
import com.utn.meraki.entity.Experiencia;
import com.utn.meraki.entity.Rubro;
import com.utn.meraki.entity.UsuarioRubro;
import com.utn.meraki.model.CertificadoModel;
import com.utn.meraki.model.ExperienciaModel;
import com.utn.meraki.model.UsuarioRubroModel;
import com.utn.meraki.repository.CertificadoRepository;
import com.utn.meraki.repository.ExperienciaRepository;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.repository.UsuarioRubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("usuarioRubroConverter")
public class UsuarioRubroConverter {

	//REPOSITORY
    @Autowired
    private UsuarioRubroRepository usuarioRubroRepository;
    @Autowired
    private RubroRepository rubroRepository;
    
    //CONVERTER
    @Autowired
    private ExperienciaConverter experienciaConverter;
    @Autowired
    private RubroConverter rubroConverter;
    @Autowired
    private CertificadoConverter certificadoConverter;
    
    public UsuarioRubro convertUsuarioRubroModelToUsuarioRubro(UsuarioRubroModel usuarioRubroModel){
        UsuarioRubro usuarioRubro = new UsuarioRubro();
        usuarioRubro.setRubro(rubroRepository.findRubroByNombreRubro(usuarioRubroModel.getRubro().getNombreRubro()));
        usuarioRubro.setFechaAsignacion(new Date(System.currentTimeMillis()));
        if(usuarioRubroModel.getCertificados()!=null) {
        	for(CertificadoModel certificado : usuarioRubroModel.getCertificados()) {
            	usuarioRubro.getCertificados().add(certificadoConverter.convertCertificadoModelToCertificado(certificado));
            }	
        }
        if(usuarioRubroModel.getExperiencias()!=null) {
        	 for(ExperienciaModel experiencia : usuarioRubroModel.getExperiencias()) {
             	usuarioRubro.getExperiencias().add(experienciaConverter.convertExperienciaModelToExperiencia(experiencia));
             }
        }
        return usuarioRubro;
    }

    private List<Experiencia> getExperiencias(List<ExperienciaModel> experienciasModel) {
        List<Experiencia> experiencias = new ArrayList<>();
        for (ExperienciaModel experienciaModel:experienciasModel){
            experiencias.add(experienciaConverter.convertExperienciaModelToExperiencia(experienciaModel));
        }
        return experiencias;
    }

    private List<Certificado> getCertificados(List<CertificadoModel> certificadosModel) {
        List<Certificado> certificados= new ArrayList<>();
        for(CertificadoModel certificadoModel: certificadosModel){
            certificados.add(certificadoConverter.convertCertificadoModelToCertificado(certificadoModel));
        }
        return certificados;
    }

    public UsuarioRubroModel convertUsuarioRubroToUsuarioRubroModel(UsuarioRubro usuarioRubro){
        UsuarioRubroModel usuarioRubroModel=new UsuarioRubroModel();
        usuarioRubroModel.setId(usuarioRubro.getId());
        usuarioRubroModel.setFechaAsignacion(usuarioRubro.getFechaAsignacion());
        usuarioRubroModel.setRubro(rubroConverter.convertRubroToRubroModel(usuarioRubro.getRubro()));
        for(Certificado certificado : usuarioRubro.getCertificados()) {
        	usuarioRubroModel.getCertificados().add(certificadoConverter.convertCertificadoToCertificadoModel(certificado));
        }
        for(Experiencia experiencia : usuarioRubro.getExperiencias()) {
        	usuarioRubroModel.getExperiencias().add(experienciaConverter.convertExperienciaToExperienciaModel(experiencia));
        }
        return usuarioRubroModel;
    }

    private List<CertificadoModel> getCertificadosModel(List<Certificado> certificados) {
        List<CertificadoModel> certificadoModels= new ArrayList<>();
        for(Certificado certificado:certificados){
            certificadoModels.add(certificadoConverter.convertCertificadoToCertificadoModel(certificado));
        }
        return  certificadoModels;
    }

    private List<ExperienciaModel> getExperienciasModel(List<Experiencia> experiencias) {
        List<ExperienciaModel> experienciaModels=new ArrayList<>();
        for(Experiencia experiencia:experiencias){
            experienciaModels.add(experienciaConverter.convertExperienciaToExperienciaModel(experiencia));
        }
        return experienciaModels;
    }
}
