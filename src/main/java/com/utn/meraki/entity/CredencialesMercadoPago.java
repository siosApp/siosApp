package com.utn.meraki.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cred_mercado_pago")

public class CredencialesMercadoPago {
	
	@Id
	@Column(name = "id_cliente", unique = true, nullable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	private String client_id;
	
	private String client_secret;
	
	private String access_token;
	
	public CredencialesMercadoPago() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
}
