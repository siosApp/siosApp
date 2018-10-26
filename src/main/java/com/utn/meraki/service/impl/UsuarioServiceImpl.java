package com.utn.meraki.service.impl;

import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;
import com.utn.meraki.converter.UsuarioConverter;
import com.utn.meraki.converter.UsuarioDestacadoConverter;
import com.utn.meraki.converter.UsuarioRubroConverter;
import com.utn.meraki.entity.Destacado;
import com.utn.meraki.entity.Rubro;
import com.utn.meraki.entity.TipoRubro;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.entity.UsuarioRubro;
import com.utn.meraki.model.FiltroModel;
import com.utn.meraki.model.UsuarioDestacadoModel;
import com.utn.meraki.model.UsuarioModel;
import com.utn.meraki.model.UsuarioRubroModel;
import com.utn.meraki.model.UsuariosByRubro;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.repository.TipoRubroRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.repository.UsuarioRubroRepository;
import com.utn.meraki.service.GeneracionCodigo;
import com.utn.meraki.service.MailService;
import com.utn.meraki.service.UsuarioService;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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
    private UsuarioRubroConverter usuarioRubroConverter;
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
    @Autowired
    private GeneracionCodigo generacionCodigo;

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
//        if(id !=null){
//        	System.out.println(id);
//            return usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findUsuarioById(id));
//        }
//        return new UsuarioModel();
    	Usuario usuario = usuarioRepository.findUsuarioById(id);
    	return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
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
    public UsuarioModel existeMail(String mail) {
        Usuario usuario=usuarioRepository.findUsuarioByMail(mail);
        if(usuario!=null){
            return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
        }
        return new UsuarioModel();
    }

    @Override
    public UsuarioModel validarMail(String mail) {
        Usuario usuario=usuarioRepository.findUsuarioByMail(mail);
        String codigo= generacionCodigo.generarCodigo();
        if(usuario !=null){
            usuario.setCodigoValidacion(codigo);
            usuario.setFechaCodigoValidacion(new Date());
            usuarioRepository.save(usuario);
            //Crear atributos codigoValidacion y fechaCodigoValidacion. Que sean persistentes por 48 hs.
            String mensaje= "<p>Hace click en el enlace de abajo para recuperar tu contraseña.\n"
                     +"</p>"
                    +"<a href='http://localhost:4200/newPassword/"+codigo+"'>Click aqui para recuperar su contraseña</a>";
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
        System.out.println("TIPO RUBRO SELECCIONADO = " +tipoRubro);
        String localidad = filtroModel.getNombreLocalidad();
        String departamento = filtroModel.getNombreDepartamento();
        String provincia = filtroModel.getNombreProvincia();
        //Filtro según elementos de búsqueda
        if(tipoRubro==null&&provincia==null) {
            for(Usuario usuario : usuarioRepository.findAll()) {
                if(usuario.getOferente()) {
                	System.out.println("USUARIO ENCONTRADO = " +usuario.getUsername());
                    listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                }
            }
        }else {
            if(tipoRubro!=null&&provincia==null) {
                if(rubro!=null) {
                    for(Usuario usuario : usuarioRepository.findAll()) {
                        if(usuario.getOferente()) {
                            for(UsuarioRubro usuarioRubro : usuarioRubroRepository.findByRubro(rubroRepository.findRubroByNombreRubro(rubro))) {
                                listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuarioRepository.
                                        findUsuarioByUsuarioRubros(usuarioRubro)));
                            }
                        }
                        break;
                    }
                }else {
                    for(Usuario usuario : usuarioRepository.findAll()) {
                        if(usuario.getOferente()) {
                            for(UsuarioRubro usuarioRubro : usuario.getUsuarioRubros()) {
                                if(usuarioRubro.getRubro().getTipoRubro().getNombreTipoRubro().equals(tipoRubro)) {
                                	System.out.println("USUARIO ENCONTRADO = " +usuario.getUsername());
                                    listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuarioRepository.
                                            findUsuarioByUsuarioRubros(usuarioRubro)));
                                }
                                break;
                            }
                        }
                    }
                }
            }else if(tipoRubro==null&&provincia!=null) {
                if(localidad==null&&departamento==null) {
                    for(Usuario usuario : usuarioRepository.findAll()) {
                        if(usuario.getOferente()) {
                            if(usuario.getDomicilio().getLocalidad().getDepartamento().getProvincia().getNombreProvincia().equals(provincia)) {
                            	System.out.println("USUARIO ENCONTRADO = " +usuario.getUsername());
                                listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                            }
                        }
                        
                    }
                }else if(departamento!=null&&localidad==null){
                    for(Usuario usuario : usuarioRepository.findAll()) {
                        if(usuario.getOferente()) {
                            if(usuario.getDomicilio().getLocalidad().getDepartamento().getNombreDepartamento().equals(departamento)) {
                                listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                            }
                        }
                    }
                }else {
                    for(Usuario usuario : usuarioRepository.findAll()) {
                        if(usuario.getOferente()) {
                            if(usuario.getDomicilio().getLocalidad().getNombreLocalidad().equals(localidad)) {
                                listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                            }
                        }
                   }
                    
                }
            }else {
            	System.out.println("FILTRO DONDE SELECCIONO TIPO DE RUBRO Y PROVINCIA");
            	System.out.println("TIPO RUBRO = " +tipoRubro +" Y PROVINCIA = " +provincia);
                if(rubro==null&&departamento==null) {
                    for(Usuario usuario : usuarioRepository.findAll()) {
                    	if(usuario.getOferente()&&usuario.getDomicilio().getLocalidad().getDepartamento().getProvincia().getNombreProvincia().equals(provincia)) {
                            for(UsuarioRubro usuarioRubro : usuario.getUsuarioRubros()) {
                                if(usuarioRubro.getRubro().getTipoRubro().getNombreTipoRubro().equals(tipoRubro)) {
                                	System.out.println("USUARIO ENCONTRADO = " +usuario.getUsername());
                                	UsuarioDestacadoModel usuarioDestacadoModel = usuarioDestacadoConverter.
                                			convertUsuarioToUsuarioDestacadoModel(usuario);
                                	if(!listUsuario.contains(usuarioDestacadoModel)) {
                                		listUsuario.add(usuarioDestacadoModel);
                                	}break;
                                }
                            }
                        }
                    }
                }else if(rubro==null&&departamento!=null) {
                    if(localidad==null) {
                        for(Usuario usuario : usuarioRepository.findAll()) {
                            if(usuario.getOferente()&&usuario.getDomicilio().getLocalidad().getDepartamento().getNombreDepartamento().equals(departamento)) {
                                listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                            }
                        }
                    }else {
                        for(Usuario usuario : usuarioRepository.findAll()) {
                            if(usuario.getOferente()&&usuario.getDomicilio().getLocalidad().getNombreLocalidad().equals(localidad)) {
                                listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                            }
                        }
                    }
                }else if(rubro!=null&&departamento==null){
                    for(Usuario usuario : usuarioRepository.findAll()) {
                        if(usuario.getOferente()) {
                            for(UsuarioRubro usuarioRubro : usuario.getUsuarioRubros()) {
                                if(usuarioRubro.getRubro().getNombreRubro().equals(rubro)) {
                                	UsuarioDestacadoModel usuarioDestacadoModel = usuarioDestacadoConverter.
                                			convertUsuarioToUsuarioDestacadoModel(usuario);
                                	if(!listUsuario.add(usuarioDestacadoModel)) {
                                		listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                                	}
                                }
                            }
                        }
                        
                    }
                }else {
                    if(localidad!=null) {
                        for(Usuario usuario : usuarioRepository.findAll()) {
                            if(usuario.getOferente()&&usuario.getDomicilio().getLocalidad().getNombreLocalidad().equals(localidad)) {
                                for(UsuarioRubro usuarioRubro : usuario.getUsuarioRubros()) {
                                    if(usuarioRubro.getRubro().getNombreRubro().equals(rubro)) {
                                        listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                                    }
                                }
                            }
                            
                        }
                    }else {
                        for(Usuario usuario : usuarioRepository.findAll()) {
                            if(usuario.getOferente()&&usuario.getDomicilio().getLocalidad().getDepartamento().getNombreDepartamento().equals(departamento)) {
                                for(UsuarioRubro usuarioRubro : usuario.getUsuarioRubros()) {
                                    if(usuarioRubro.getRubro().getNombreRubro().equals(rubro)) {
                                        listUsuario.add(usuarioDestacadoConverter.convertUsuarioToUsuarioDestacadoModel(usuario));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return listUsuario;
    }

    @Override
    public UsuarioModel cambiarContrasena(String mail, String password, String codigo) {
        Usuario usuario=usuarioRepository.findUsuarioByMail(mail);
        if(usuario!=null &&  usuario.getFechaCodigoValidacion()!=null){
            //Calculando diferencia dias
            LocalDateTime hasta = LocalDateTime.now();
            LocalDateTime desde = new LocalDateTime(usuario.getFechaCodigoValidacion());
            Period period = new Period(hasta, desde);
            long diasDiferencia = Math.abs(period.getDays());
            //
            if(diasDiferencia<=2 && codigo.equals(usuario.getCodigoValidacion())){
                usuario.setPassword(password);
                usuario.setFechaCodigoValidacion(null);
                usuario.setCodigoValidacion(null);
                usuarioRepository.save(usuario);
                return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
            }
        }
        return new UsuarioModel();
    }

	@Override
	public UsuarioModel asignarOferente(UsuarioModel usuarioModel) {
		Usuario usuario = usuarioRepository.findUsuarioById(usuarioModel.getId());
		usuario.setOferente(true);
		for(UsuarioRubroModel usuarioRubroModel : usuarioModel.getUsuarioRubros()) {
			UsuarioRubro usuarioRubro = usuarioRubroConverter.convertUsuarioRubroModelToUsuarioRubro(usuarioRubroModel);
			usuarioRubroRepository.save(usuarioRubro);
			usuario.getUsuarioRubros().add(usuarioRubro);
		}
		usuarioRepository.save(usuario);
		return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
	}


    @Override
    public UsuarioModel addRubro(String idUsuario, String nombreRubro, String nombreTipoRubro) {
        Usuario  usuario = usuarioRepository.findUsuarioById(idUsuario);
        TipoRubro tipoRubro=tipoRubroRepository.findTipoRubroByNombreTipoRubro(nombreTipoRubro);
        Rubro rubro= rubroRepository.findRubroByNombreRubroAndTipoRubro(nombreRubro,tipoRubro);
        //Asignar Rubro al oferente.
        UsuarioRubro usuarioRubro= new UsuarioRubro();
        usuarioRubro.setRubro(rubro);
        usuarioRubro.setFechaAsignacion(new Date());
        usuario.addRubro(usuarioRubro);
        usuario.setOferente(tieneRubros(usuario));
        usuarioRubroRepository.save(usuarioRubro);
        usuarioRepository.save(usuario);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

    @Override
    public UsuarioModel eliminarRubro(String idUsuario, String idUsuarioRubro) {
        Usuario usuario= usuarioRepository.findUsuarioById(idUsuario);
        UsuarioRubro usuarioRubro=usuarioRubroRepository.findById(idUsuarioRubro);
        usuario.eliminarRubro(usuarioRubro);
        usuario.setOferente(tieneRubros(usuario));
        usuarioRubroRepository.delete(usuarioRubro);
        usuarioRepository.save(usuario);
        return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
    }

	@Override
	public List<UsuariosByRubro> cantidadUsuariosByRubro() {
		List<UsuariosByRubro> listUsuariosByRubro = new ArrayList<>();
		for(Rubro rubro : rubroRepository.findAll()) {
			UsuariosByRubro usuariosByRubro = new UsuariosByRubro();
			usuariosByRubro.setNombreRubro(rubro.getNombreRubro());
			Integer cantUsuario = 0;
			for(UsuarioRubro usuarioRubro : usuarioRubroRepository.findByRubro(rubro)) {
				cantUsuario += 1;
			}
			usuariosByRubro.setCantidadUsuarios(cantUsuario);
			listUsuariosByRubro.add(usuariosByRubro);
		}
		return listUsuariosByRubro;
	}

    @Override
    public UsuarioModel getUsuarioByUsername(String username) {
        return usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findUsuarioByUsername(username));
    }

    private boolean tieneRubros(Usuario usuario){
        if(usuario.getUsuarioRubros()==null || usuario.getUsuarioRubros().isEmpty()){
            return false;
        }
        return true;
    }

	@Override
	public UsuarioModel registrarUsuarioLogueado(String idUsuario) {
		Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
		usuario.setLogueado(true);
		usuarioRepository.save(usuario);
		return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
	}

	@Override
	public UsuarioModel registrarUsuarioDeslogueado(String idUsuario) {
		Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
		usuario.setLogueado(false);
		usuarioRepository.save(usuario);
		return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
	}

	@Override
	public Integer calcularCantidadUsuariosLinea() {
		Integer cantidadUsuarios = 0;
		for(Usuario usuario : usuarioRepository.findAll()) {
			if(usuario.isLogueado()) {
				cantidadUsuarios += 1;
			}
		}
		return cantidadUsuarios;
	}

	
}
