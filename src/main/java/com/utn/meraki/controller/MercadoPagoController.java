package com.utn.meraki.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MP;
import com.utn.meraki.entity.CredencialesMercadoPago;
import com.utn.meraki.entity.Destacado;
import com.utn.meraki.model.DatoPagoMercadoPagoModel;
import com.utn.meraki.model.EstadoDestacadoModel;
import com.utn.meraki.repository.DestacadoRepository;
import com.utn.meraki.repository.EstadoDestacadoRepository;
import com.utn.meraki.repository.MedioPagoRepository;
import com.utn.meraki.repository.UsuarioRepository;
import com.utn.meraki.service.impl.CredencialesMercadoPagoServiceImpl;

@CrossOrigin
@RequestMapping("/reservasService")
@RestController

public class MercadoPagoController extends HttpServlet{
	
	@Autowired
	@Qualifier("credencialesMercadoPagoService")
	CredencialesMercadoPagoServiceImpl credencialesMercadoPagoServiceImpl;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	MedioPagoRepository medioPagoRepository;
	@Autowired
	EstadoDestacadoRepository estadoDestacadoRepository;
	@Autowired
	DestacadoRepository destacadoRepository;
	
	@PostMapping("/pago/mercadopago")
	public String realizarPago(@RequestBody DatoPagoMercadoPagoModel datoPagoMercadoPagoModel) throws JSONException, Exception{
		
		List<CredencialesMercadoPago> credenciales = credencialesMercadoPagoServiceImpl.findAll();
		MP mp = new MP(credenciales.get(0).getClient_id(),credenciales.get(0).getClient_secret());
		String accessToken = mp.getAccessToken();
		System.out.println(accessToken);
		System.out.println(credenciales.get(0).getAccess_token());
		
		String preferenceData = "{'items':"+
				"[{"+
					"'title':"+ datoPagoMercadoPagoModel.getTitle()+","+
					"'quantity':"+ datoPagoMercadoPagoModel.getQuantity()+","+
					"'currency_id':"+ datoPagoMercadoPagoModel.getCurrency_id()+","+
					"'unit_price':"+ datoPagoMercadoPagoModel.getUnit_price()+
				"}]"+
			"}";
		System.out.println("preferencias" +preferenceData);
		JSONObject preference = mp.createPreference(preferenceData);
		
		String initPoint = preference.getJSONObject("response").getString("init_point");
		System.out.println("initPoint" + initPoint);
		
		//Pone al usuario como destacado
		Destacado destacado = new Destacado();
		destacado.setMonto(datoPagoMercadoPagoModel.getUnit_price());
		destacado.setUsuario(usuarioRepository.findUsuarioById(datoPagoMercadoPagoModel.getIdUsuario()));
		destacado.setMedioPago(medioPagoRepository.findMedioPagoById(datoPagoMercadoPagoModel.getIdMedioPago()));
		destacado.setEstadoDestacado(estadoDestacadoRepository.findEstadoDestacadoByNombreEstadoDestacado("Destacado"));
		
		Date fechaDestacado = new Date(System.currentTimeMillis());
		destacado.setFechaDestacado(fechaDestacado);
		
		Calendar vto = Calendar.getInstance();
		vto.setTime(fechaDestacado);
		vto.add(Calendar.MONTH, 1);
		Date fechaVto = new Date(vto.getTime().getTime());
		destacado.setFechaVtoDestacado(fechaVto);
		
		destacadoRepository.save(destacado);
		
		return initPoint;
					
	}

}
