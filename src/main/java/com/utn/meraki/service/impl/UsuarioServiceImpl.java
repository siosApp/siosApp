package com.utn.meraki.service.impl;

import com.utn.meraki.converter.UsuarioConverter;
import com.utn.meraki.converter.UsuarioDestacadoConverter;
import com.utn.meraki.converter.UsuarioRubroConverter;
import com.utn.meraki.entity.Destacado;
import com.utn.meraki.entity.Rubro;
import com.utn.meraki.entity.TipoRubro;
import com.utn.meraki.entity.Usuario;
import com.utn.meraki.entity.UsuarioRubro;
import com.utn.meraki.model.*;
import com.utn.meraki.repository.DestacadoRepository;
import com.utn.meraki.repository.RubroRepository;
import com.utn.meraki.repository.TipoRubroRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.repository.UsuarioRubroRepository;
import com.utn.meraki.service.GeneracionCodigo;
import com.utn.meraki.service.MailService;
import com.utn.meraki.service.UsuarioService;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    static final String ESTADO_SOLICITUD_FINALIZADA_QUERY = "SELECT id FROM estados_solicitudes WHERE nombre_estado_solicitud LIKE 'Finalizada'";

    static final String SOLICITUDES_FINALIZADAS_POR_USUARIO_DEMANDANTE_QUERY = "SELECT id FROM solicitudes WHERE id_demandante = :id AND id IN \n" +
            "(SELECT id_solicitud FROM solicitudes_estados \n" +
            "WHERE id_estado_solicitud = ("+ESTADO_SOLICITUD_FINALIZADA_QUERY+")) ";

    static final String SOLICITUDES_FINALIZADAS_POR_USUARIO_QUERY = "SELECT id FROM solicitudes WHERE (id_demandante = :id OR id_oferente = :id) AND id IN \n" +
            "(SELECT id_solicitud FROM solicitudes_estados \n" +
            "WHERE id_estado_solicitud = ("+ESTADO_SOLICITUD_FINALIZADA_QUERY+")) ";

    static final String SOLICITUDES_FINALIZADAS_POR_USUARIO_OFERENTE_QUERY = "SELECT id FROM solicitudes WHERE id_oferente = :id AND id IN \n" +
            "(SELECT id_solicitud FROM solicitudes_estados \n" +
            "WHERE id_estado_solicitud = ("+ESTADO_SOLICITUD_FINALIZADA_QUERY+")) ";

    static final String USUARIOS_CON_COMENTARIOS = "SELECT DISTINCT calificacion, fecha_calificacion, u.mail,u.username,com.descripcion AS 'comentario',u.imagen,u.id " +
            "FROM calificaciones cal INNER JOIN usuarios u ON u.id = id_usuario INNER JOIN comentarios com ON com.id_calificacion = cal.id " +
            "WHERE id_solicitud IN ("+SOLICITUDES_FINALIZADAS_POR_USUARIO_DEMANDANTE_QUERY+") ORDER BY fecha_calificacion DESC";

    static final String CALIFICACIONES_DEMANDANTE_QUERY = "SELECT DISTINCT cal.calificacion, cal.fecha_calificacion, u.mail,u.username,u.imagen,s.fecha_solicitud,s.descripcion,r.nombre_rubro\n" +
            "FROM calificaciones cal " +
            "INNER JOIN usuarios u ON u.id = cal.id_usuario " +
            "INNER JOIN solicitudes s ON s.id = cal.id_solicitud " +
            "INNER JOIN rubros r ON r.id = s.id_rubro " +
            "WHERE cal.id_usuario != :id AND cal.id_solicitud IN ("+SOLICITUDES_FINALIZADAS_POR_USUARIO_DEMANDANTE_QUERY+") " +
            "ORDER BY cal.fecha_calificacion DESC";

    static final String CALIFICACIONES_OFERENTE_QUERY = "SELECT DISTINCT cal.calificacion, cal.fecha_calificacion, u.mail,u.username,u.imagen,s.fecha_solicitud,s.descripcion,r.nombre_rubro\n" +
            "FROM calificaciones cal " +
            "INNER JOIN usuarios u ON u.id = cal.id_usuario " +
            "INNER JOIN solicitudes s ON s.id = cal.id_solicitud " +
            "INNER JOIN rubros r ON r.id = s.id_rubro " +
            "WHERE cal.id_usuario != :id AND cal.id_solicitud IN ("+SOLICITUDES_FINALIZADAS_POR_USUARIO_OFERENTE_QUERY+") " +
            "ORDER BY cal.fecha_calificacion DESC";

    static final String CALIFICACIONES_QUERY = "SELECT DISTINCT cal.calificacion, cal.fecha_calificacion, u.mail,u.username,u.imagen,s.fecha_solicitud,s.descripcion,r.nombre_rubro\n" +
            "FROM calificaciones cal " +
            "INNER JOIN usuarios u ON u.id = cal.id_usuario " +
            "INNER JOIN solicitudes s ON s.id = cal.id_solicitud " +
            "INNER JOIN rubros r ON r.id = s.id_rubro " +
            "WHERE cal.id_usuario != :id AND cal.id_solicitud IN ("+SOLICITUDES_FINALIZADAS_POR_USUARIO_QUERY+") " +
            "ORDER BY cal.fecha_calificacion DESC";

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
    @PersistenceContext
    private EntityManager entityManager;

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
      //ORDENAMIENTO
      Iterator lista = listUsuario.iterator();
      while (lista.hasNext()) {
    	  UsuarioDestacadoModel elementoLista = (UsuarioDestacadoModel)lista.next();
            }
      Collections.sort(listUsuario);
      for(UsuarioDestacadoModel rubros : listUsuario) {
      	System.out.println("RUBRO " +rubros.getDestacado() + " CANTIDAD " +rubros.getUsername());
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

    @Override
    public List<UsuarioModel> getUsuariosRegistradosFiltered(String sexo, Integer edadDesde, Integer edadHasta, String provincia, String departamento,
                                                             String localidad, String tipoRubro, String rubro) {
        Calendar calendar=Calendar.getInstance();
        //calendar.set(new Date().getYear()-edadDesde,new Date().getMonth(),new Date().getDay());

        calendar.add(Calendar.YEAR,-edadDesde);
        Date fechaDesde= calendar.getTime();
        Calendar calendarHasta=Calendar.getInstance();
        calendarHasta.add(Calendar.YEAR,-edadHasta);
        //calendarHasta.set(new Date().getYear()-edadHasta,new Date().getMonth(),new Date().getDay());
        Date fechaHasta= calendarHasta.getTime();
        List<UsuarioModel> usuarioModels= new ArrayList<>();
        String hql= new String();
        hql += "select u from Usuario u join u.usuarioRubros r where 1=1 ";
        if(sexo!=null){
            hql +=" and u.sexo = :sexo ";
        }
        if(edadDesde !=null && edadHasta !=null){
            //Armar fecha
            hql +="and u.fechaNacimiento between :fechaDesde and :fechaHasta ";
        }
        if(provincia !=null && departamento == null && localidad == null){
            hql += "and u.domicilio.localidad.departamento.provincia.nombreProvincia = :provincia ";
        }
        if(departamento !=null && provincia !=null && localidad == null){
            hql += "and u.domicilio.localidad.departamento.nombreDepartamento = :departamento and u.domicilio.localidad.departamento.provincia.nombreProvincia = :provincia ";
        }
        if(localidad!=null && departamento !=null && provincia !=null){
            hql += "and u.domicilio.localidad.nombreLocalidad = :localidad and u.domicilio.localidad.departamento.nombreDepartamento = :departamento " +
                    "and u.domicilio.localidad.departamento.provincia.nombreProvincia = :provincia ";
        }
        if(rubro !=null && tipoRubro != null){
            hql += "and r.rubro.nombreRubro = :rubro and r.rubro.tipoRubro.nombreTipoRubro = :tipoRubro ";
        }
        if(rubro == null && tipoRubro !=null){
            hql += "and r.rubro.tipoRubro.nombreTipoRubro = :tipoRubro ";
        }
        Query query = entityManager.createQuery(hql);
        if(sexo!=null){
            query.setParameter("sexo",sexo);
        }
        if(edadDesde !=null && edadHasta !=null){
            //Armar fecha
            query.setParameter("fechaDesde",fechaHasta).setParameter("fechaHasta",fechaDesde);
        }
        if(provincia !=null && departamento == null && localidad == null){
            query.setParameter("provincia",provincia).setParameter("departamento",departamento).setParameter("localidad",localidad);
        }
        if(departamento !=null && provincia !=null && localidad == null){
            query.setParameter("departamento",departamento).setParameter("provincia",provincia);
        }
        if(localidad!=null && departamento !=null && provincia !=null){
            query.setParameter("localidad",localidad).setParameter("departamento",departamento).setParameter("provincia",provincia);
        }
        if(rubro !=null && tipoRubro != null){
            query.setParameter("rubro",rubro).setParameter("tipoRubro",tipoRubro);
        }
        if(rubro == null && tipoRubro !=null){
            query.setParameter("tipoRubro",tipoRubro);
        }
        List<Usuario> usuarios=query.getResultList();
        for(Usuario usuario:usuarios){
            if(!estaEnLaLista(usuario.getId(),usuarioModels)){
                usuarioModels.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
            }
        }
        return usuarioModels;
    }

    private boolean estaEnLaLista(String idUsuario,List<UsuarioModel>usuarioModels){
        for(UsuarioModel usuarioModel:usuarioModels){
            if(usuarioModel.getId().equals(idUsuario)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UsuarioComentariosModel> getUsuarioConComentarios(String id) {
        Query query = entityManager.createNativeQuery(USUARIOS_CON_COMENTARIOS).setParameter("id",id);
        return parseResultQueryUsuarioComentario(query.getResultList());
    }

    /**
     * Parsea una consulta sql a una lista de usuarios que comentaron en calidad de oferente, al usuario demandante
     *
     * @param rows
     * @return
     */
    private List<UsuarioComentariosModel> parseResultQueryUsuarioComentario(List<Object[]> rows){
        return rows.stream().map( row -> getOneUsuarioComentariosModel(row)).collect(Collectors.toList());
    }

    /**
     * Parsea un sólo resultado a un objeto UsuarioComentarioModel para facilitar el uso del lambda
     *
     * @param row
     * @return
     */
    private UsuarioComentariosModel getOneUsuarioComentariosModel(Object[] row){

        UsuarioComentariosModel model= new UsuarioComentariosModel();
        model.setCalificacion(Integer.valueOf(row[0].toString()));
        try {
            model.setFechaCalificacion(new SimpleDateFormat("yyyy-MM-dd").parse(row[1].toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.setEmail(row[2].toString());
        model.setUsername(row[3].toString());
        model.setComentario(row[4].toString());
        model.setImagen(row[5].toString());
        model.setIdUsuario(row[6].toString());
        return model;
    }

    @Override
    public ReporteCalificacionDTO getUsuariosQueMeCalificaron(String idUsuario, boolean isDemandante, boolean isOferente) {
        String sqlQuery = new String();
        if(isDemandante && !isOferente){
            sqlQuery=CALIFICACIONES_DEMANDANTE_QUERY;
        }
        else if(!isDemandante && isOferente){
            sqlQuery=CALIFICACIONES_OFERENTE_QUERY;
        }
        else if(isDemandante && isOferente){
            sqlQuery=CALIFICACIONES_QUERY;
        }

        Query query=entityManager.createNativeQuery(sqlQuery).setParameter("id",idUsuario);

        return buildReporte(query.getResultList());
    }

    private ReporteCalificacionDTO buildReporte(List<Object[]> rows){
        ReporteCalificacionDTO dto = new ReporteCalificacionDTO();
        rows.stream().forEach( row -> setCalificacion(row[0].toString(),dto));
        rows.stream().forEach( row -> {
            try {
                buildOneUsuarioCalifica(row,dto);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return dto;
    }

    /**
     * Construye un usuario que calificó al usuario buscado
     *
     * @param row
     * @param dto
     * @throws ParseException
     */
    private void buildOneUsuarioCalifica(Object[] row, ReporteCalificacionDTO dto) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        UsuarioCalificaDTO usuarioCalificaDTO = new UsuarioCalificaDTO();
        usuarioCalificaDTO.setCalificacion(Integer.valueOf(row[0].toString()));
        usuarioCalificaDTO.setFechaCalificacion(simpleDateFormat.parse(row[1].toString()));
        usuarioCalificaDTO.setEmail(row[2].toString());
        usuarioCalificaDTO.setUsername(row[3].toString());
        usuarioCalificaDTO.setUrlImagen(row[4].toString());
        usuarioCalificaDTO.setFechaSolicitud(simpleDateFormat.parse(row[5].toString()));
        usuarioCalificaDTO.setDescripcionSolicitud(row[6].toString());
        usuarioCalificaDTO.setRubro(row[7].toString());
        dto.getDtoList().add(usuarioCalificaDTO);
    }

    /**
     * Barre todo el arreglo contando cada calificacion recibida y setea la cuenta al dto
     *
     * @param row
     * @param dto
     */
    private void setCalificacion( String row,ReporteCalificacionDTO dto){

        if (row == "5") {
            Integer calificacion = dto.getCantidadUsuariosCinco();
            dto.setCantidadUsuariosCinco(calificacion + Integer.valueOf(row));
        }
        else if (row == "4") {
            Integer calificacion = dto.getCantidadUsuariosCuatro();
            dto.setCantidadUsuariosCuatro(calificacion + Integer.valueOf(row));
        }
        else if (row == "3") {
            Integer calificacion = dto.getCantidadUsuariosTres();
            dto.setCantidadUsuariosTres(calificacion + Integer.valueOf(row));
        }
        else if (row == "2") {
            Integer calificacion = dto.getCantidadUsuariosDos();
            dto.setCantidadUsuariosDos(calificacion + Integer.valueOf(row));
        }
        else if (row == "1") {
            Integer calificacion = dto.getCantidadUsuariosUno();
            dto.setCantidadUsuariosUno(calificacion + Integer.valueOf(row));
        }
    }
}
