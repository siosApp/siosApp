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
import com.utn.meraki.model.UsuariosRegistradosDestacadosModel;
import com.utn.meraki.repository.DestacadoRepository;
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
import java.util.Calendar;
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
    private DestacadoRepository destacadoRepository;
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
//        if(id != null){
    	if(usuarioRepository.findUsuarioById(id)!=null){
        	System.out.println(id);
            return usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findUsuarioById(id));
        }else {
        	return new UsuarioModel();
        }
//        Usuario usuario = usuarioRepository.findUsuarioById(id);
//    	return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
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
//        System.out.println("RUBRO SELECCIONADO = " +rubro);
        String tipoRubro = filtroModel.getNombreTipoRubro();
//        System.out.println("TIPO RUBRO SELECCIONADO = " +tipoRubro);
        String localidad = filtroModel.getNombreLocalidad();
//        System.out.println("LOCALIDAD SELECCIONADA = " +localidad);
        String departamento = filtroModel.getNombreDepartamento();
//        System.out.println("DEPARTAMENTO SELECCIONADO = " +departamento);
        String provincia = filtroModel.getNombreProvincia();
//        System.out.println("PROVINCIA SELECCIONADA = " +provincia);
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
            	System.out.println("TIPO RUBRO NULL && PROVINCIA SELECCIONADA");
                if(localidad==null&&departamento==null) {
                	System.out.println("LOCALIDAD NULL && DEPARTAMENTO NULL");
                    for(Usuario usuario : usuarioRepository.findAll()) {
//                    	System.out.println("USUARIO OFERNTE? = " +usuario.getOferente());
//                    	System.out.println("PROVINCIA DEL USUARIO = " +usuario.getDomicilio().getLocalidad().getDepartamento().getProvincia().getNombreProvincia());
                        if(usuario.getOferente()) {
                        	System.out.println("EL USUARIO ES OFERENTE");
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

	@Override
	public UsuariosRegistradosDestacadosModel cantidadUsuariosRegistradosDestacados(Date fechaDesde, Date fechaHasta) {
		UsuariosRegistradosDestacadosModel usuariosRegistrados = new UsuariosRegistradosDestacadosModel();
		Integer registrados = 0;
		Integer destacados = 0;
		for(Usuario usuario : usuarioRepository.findAll()) {
			if(usuario.getFechaRegistro()!=null) {
				if(usuario.getFechaRegistro().after(fechaDesde)&&usuario.getFechaRegistro().before(fechaHasta)) {
					registrados += 1;
				}
			}
		}
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaDestacado()!=null) {
				if(destacado.getFechaDestacado().after(fechaDesde)&&destacado.getFechaDestacado().before(fechaHasta)) {
					destacados += 1;
				}
			}
		}
		usuariosRegistrados.setDestacados(destacados);
		usuariosRegistrados.setRegistrados(registrados);
		return usuariosRegistrados;
	}

	@Override
	public List<UsuariosRegistradosDestacadosModel> registradosDestacadosUltimosMeses() {
		List<UsuariosRegistradosDestacadosModel> listaModel = new ArrayList<>();
		//Setéo de fechas para filtrar por los últimos 3 meses
		Date fechaActual = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		calendar.add(calendar.MONTH, -1);
		Date fechaAnterior1 = new Date(calendar.getTime().getTime());
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(fechaActual);
		calendar.add(calendar.MONTH, -2);
		Date fechaAnterior2 = new Date(calendar.getTime().getTime());
		//Filtro y conteo por mes
		//Mes actual
		UsuariosRegistradosDestacadosModel lista1 = new UsuariosRegistradosDestacadosModel();
		Integer registrados1 = 0;
		Integer destacados1 = 0;
		for(Usuario usuario : usuarioRepository.findAll()) {
			if(usuario.getFechaRegistro()!=null) {
				if(fechaActual.getMonth()==usuario.getFechaRegistro().getMonth()
						&&fechaActual.getYear()==usuario.getFechaRegistro().getYear()) {
					registrados1 += 1;
				}
			}
		}
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaDestacado()!=null) {
				if(fechaActual.getMonth()==destacado.getFechaDestacado().getMonth()
						&&fechaActual.getYear()==destacado.getFechaDestacado().getYear()) {
					destacados1 += 1;
				}
			}
		}
		lista1.setDestacados(destacados1);
		lista1.setRegistrados(registrados1);
		listaModel.add(lista1);
		//Mes anterior
		UsuariosRegistradosDestacadosModel lista2 = new UsuariosRegistradosDestacadosModel();
		Integer registrados2 = 0;
		Integer destacados2 = 0;
		for(Usuario usuario : usuarioRepository.findAll()) {
			if(usuario.getFechaRegistro()!=null) {
				if(fechaAnterior1.getMonth()==usuario.getFechaRegistro().getMonth()
						&&fechaAnterior1.getYear()==usuario.getFechaRegistro().getYear()) {
					registrados2 += 1;
				}
			}
		}
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaDestacado()!=null) {
				if(fechaAnterior1.getMonth()==destacado.getFechaDestacado().getMonth()
						&&fechaAnterior1.getYear()==destacado.getFechaDestacado().getYear()) {
					destacados2 += 1;
				}
			}
		}
		lista2.setDestacados(destacados2);
		lista2.setRegistrados(registrados2);
		listaModel.add(lista2);
		//Hace 3 meses
		UsuariosRegistradosDestacadosModel lista3 = new UsuariosRegistradosDestacadosModel();
		Integer registrados3 = 0;
		Integer destacados3 = 0;
		for(Usuario usuario : usuarioRepository.findAll()) {
			if(usuario.getFechaRegistro()!=null) {
				if(fechaAnterior2.getMonth()==usuario.getFechaRegistro().getMonth()
						&&fechaAnterior2.getYear()==usuario.getFechaRegistro().getYear()) {
					registrados3 += 1;
				}
			}
		}
		for(Destacado destacado : destacadoRepository.findAll()) {
			if(destacado.getFechaDestacado()!=null) {
				if(fechaAnterior2.getMonth()==destacado.getFechaDestacado().getMonth()
						&&fechaAnterior2.getYear()==destacado.getFechaDestacado().getYear()) {
					destacados3 += 1;
				}
			}
		}
		lista3.setDestacados(destacados3);
		lista3.setRegistrados(registrados3);
		listaModel.add(lista3);
		return listaModel;
	}

	@Override
	public Integer cantidadUsuariosRegistrados() {
		Integer cantidad = 0;
		for(Usuario usuario : usuarioRepository.findAll()) {
			if(usuario.getFechaBaja()==null) {
				cantidad += 1;
			}
		}
		return cantidad;
	}

    @Override
    public List<UsuarioModel> listUsuarioEnLinea() {
        List<UsuarioModel> usuarioModels= new ArrayList<>();
        for (Usuario usuario:usuarioRepository.findByLogueado(true)){
            usuarioModels.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
        }
        return usuarioModels;
    }

}
