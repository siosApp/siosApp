package com.utn.meraki.controller;

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
import com.utn.meraki.model.DatoPagoMercadoPagoModel;
import com.utn.meraki.service.impl.CredencialesMercadoPagoServiceImpl;

@CrossOrigin
@RequestMapping("/reservasService")
@RestController

public class MercadoPagoController extends HttpServlet{
	
	@Autowired
	@Qualifier("credencialesMercadoPagoService")
	CredencialesMercadoPagoServiceImpl credencialesMercadoPagoServiceImpl;
	
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
		
		return initPoint;
					
	}

}
