package com.utn.meraki.converter;

import com.utn.meraki.entity.Certificado;
import com.utn.meraki.entity.Experiencia;
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
import java.util.List;

@Component("usuarioRubroConverter")
public class UsuarioRubroConverter {

    @Autowired
    private RubroRepository rubroRepository;
    @Autowired
    private ExperienciaRepository experienciaRepository;
    @Autowired
    private CertificadoRepository certificadoRepository;
    @Autowired
    private UsuarioRubroRepository usuarioRubroRepository;
    @Autowired
    private RubroConverter rubroConverter;
    @Autowired
    private ExperienciaConverter experienciaConverter;
    @Autowired
    private CertificadoConverter certificadoConverter;

    public UsuarioRubro convertUsuarioRubroModelToUsuarioRubro(UsuarioRubroModel usuarioRubroModel){
        UsuarioRubro usuarioRubro=new UsuarioRubro();
        if(usuarioRubroModel.getId()!=null){
            usuarioRubro=usuarioRubroRepository.findById(usuarioRubroModel.getId());
        }
        usuarioRubro.setRubro(rubroConverter.convertRubroModelToRubro(usuarioRubroModel.getRubro()));
        usuarioRubro.setFechaAsignacion(usuarioRubroModel.getFechaAsignacion());
        usuarioRubro.setCertificados(getCertificados(usuarioRubroModel.getCertificados()));
        usuarioRubro.setExperiencias(getExperiencias(usuarioRubroModel.getExperiencias()));
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
        usuarioRubroModel.setExperiencias(getExperienciasModel(usuarioRubro.getExperiencias()));
        usuarioRubroModel.setCertificados(getCertificadosModel(usuarioRubro.getCertificados()));
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
