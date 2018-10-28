package pruebasSeguridad;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import configuracionPruebas.ConfiguracionGeneralPruebas;

public class CerrarSesionAutomaticamente extends ConfiguracionGeneralPruebas {
	
	private String botonPerfil = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[2]/a";

	@Test
	public void intentarUsarSistemaDespuesDeDiezSegundos() {

		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("eduardo");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
				
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		// duerme al hilo que se esta ejecutando 10 segundos
	    try {
	        Thread.sleep(10*1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
	    // Intenta seleccionar el boton de perfil
		driver.findElement(By.xpath(botonPerfil)).click();
		
	}

}

