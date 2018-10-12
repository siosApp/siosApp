package com.utn.meraki.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.utn.meraki.entity.CredencialesMercadoPago;
import com.utn.meraki.repository.CredencialesMercadoPagoRepository;
import com.utn.meraki.service.CredencialesMercadoPagoService;

@Service("credencialesMercadoPagoService")

public class CredencialesMercadoPagoServiceImpl implements CredencialesMercadoPagoService{

	@Autowired
	@Qualifier("credencialesMercadoPagoRepository")
	CredencialesMercadoPagoRepository credencialesMercadoPagoRepository;
	
	@Override
	public List<CredencialesMercadoPago> findAll() {
		return credencialesMercadoPagoRepository.findAll();
	}

}
