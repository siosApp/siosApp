package com.utn.meraki.service.impl;

import com.utn.meraki.converter.UsuarioConverter;
import com.utn.meraki.converter.UsuarioDestacadoConverter;
import com.utn.meraki.entity.TipoRubro;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.entity.UsuarioRubro;
import com.utn.meraki.model.FiltroModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.repository.TipoRubroRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.repository.UsuarioRubroRepository;
import com.utn.meraki.service.MailService;
import com.utn.meraki.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
    @Autowired
    private UsuarioConverter usuarioConverter;
    @Autowired
    private UsuarioDestacadoConverter usuarioDestacadoConverter;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioRubroRepository usuarioRubroRepository;
    @Autowired
    private TipoRubroRepository tipoRubroRepository;
    @Autowired
    private RubroRepository rubroRepository;
    @Autowired
    private MailService mailService;

    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioConverter.convertUsuarioModelToUsuario(usuarioModel);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public UsuarioModel editarUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioConverter.convertUsuarioModelToUsuario(usuarioModel);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public List<UsuarioModel> listUsuarioVigente() {
        List<UsuarioModel> listusuarios = new ArrayList<>();
        for(Usuario usuario : usuarioRepository.findAll()) {
            if(usuario.getFechaBaja()==null) {
                listusuarios.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
            }
        }
        return listusuarios;
    }

    @Override
    public UsuarioModel habilitarUsuario(String id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        usuario.setFechaBaja(null);
        usuarioRepository.save(usuario);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public List<UsuarioModel> listUsuarioTodas() {
        List<UsuarioModel> listUsuarios = new ArrayList<>();
        for(Usuario usuario : usuarioRepository.findAll()) {
            listUsuarios.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
        }
        return listUsuarios;
    }

    @Override
    public UsuarioModel getUsuarioById(String id) {
        if(id !=null){
            return usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findUsuarioById(id));
        }
        return new UsuarioModel();
    }

    @Override
    public UsuarioModel deshabilitarUsuario(String id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        usuario.setFechaBaja(new java.util.Date());
        usuarioRepository.save(usuario);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

	@Override //Loguea usuario, si existe devuelve modelo y setea ultima fecha de ingreso
	public UsuarioModel loguearUsuario(String username, String password) {
		Usuario usuario = null;
		if(usuarioRepository.findUsuarioByusernameAndPassword(username, password)!=null) {
			usuario = usuarioRepository.findUsuarioByusernameAndPassword(username, password);
			usuario.setFechaUltIngreso(new Date());
			usuarioRepository.save(usuario);
		}else {
			usuario = new Usuario();
		}
		return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
	}

    @Override
    public UsuarioModel existeUsuario(String username) {
        Usuario usuario=usuarioRepository.findUsuarioByUsername(username);
        if(usuario!=null){
            return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
        }
        return new UsuarioModel();
    }

    @Override
    public UsuarioModel validarMail(String mail) {
        Usuario usuario=usuarioRepository.findUsuarioByMail(mail);
        if(usuario !=null){
            //Crear atributos codigoValidacion y fechaCodigoValidacion. Que sean persistentes por 48 hs.
            String mensaje= "Hace click en el enlace de abajo para recuperar tu contraseña."
                    +"";
            mailService.enviarMail(mail,"Recuperacion de contraseña",mensaje);
            return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
        }
        return new UsuarioModel();
    }

    @Override
	public List<UsuarioDestacadoModel> filtrarUsuarios(FiltroModel filtroModel) {
		List<UsuarioDestacadoModel> listUsuario = new ArrayList<>();
		//Seteo atributos el filtro
		String rubro = filtroModel.getNombreRubro();
		String tipoRubro = filtroModel.getNombreTipoRubro();
		String localidad = filtroModel.getNombreLocalidad();
		String departamento = filtroModel.getNombreDepartamento();
		String provincia = filtroModel.getNombreProvincia();
		//Filtro según elementos de búsqueda
		if(tipoRubro==null&&provincia==null) {
			for(Usuario usuario : usuarioRepository.findAll()) {
				listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
			}
		}else {
			if(tipoRubro!=null&&provincia==null) {
				if(rubro!=null) {
					for(Usuario usuario : usuarioRepository.findAll()) {
						for(UsuarioRubro usuarioRubro : usuarioRubroRepository.findByRubro(rubroRepository.findRubroByNombreRubro(rubro))) {
							listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuarioRepository.
									findUsuarioByUsuarioRubros(usuarioRubro)));
						}
					}
				}else {
					for(Usuario usuario : usuarioRepository.findAll()) {
						for(UsuarioRubro usuarioRubro : usuarioRubroRepository.findByRubro(rubroRepository.findRubroByNombreRubro(rubro))) {
							if(usuarioRubro.getRubro().getTipoRubro().getNombreTipoRubro().equals(tipoRubro)) {
								listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuarioRepository.
										findUsuarioByUsuarioRubros(usuarioRubro)));
							}
						}
					}
				}
			}else if(tipoRubro==null&&provincia!=null) {
				if(localidad==null&&departamento==null) {
					for(Usuario usuario : usuarioRepository.findAll()) {
						if(usuario.getDomicilio().getLocalidad().getDepartamento().getProvincia().equals(provincia)) {
							listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
						}
					}
				}else if(departamento!=null&&localidad==null){
					for(Usuario usuario : usuarioRepository.findAll()) {
						if(usuario.getDomicilio().getLocalidad().getDepartamento().equals(departamento)) {
							listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
						}
					}
				}else {
					for(Usuario usuario : usuarioRepository.findAll()) {
						if(usuario.getDomicilio().getLocalidad().equals(localidad)) {
							listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
						}
					}
				}
			}else {
				//Falta desarrollar para el caso de que seleccione tipo de rubro y provincia
				//A partir de aqui ver todas las posibilidades para ver si selecciona rubro, localidad, departamentos
				//Y filtrar con todas las combinaciones posibles
			}
		}
		return listUsuario;
	}
}
