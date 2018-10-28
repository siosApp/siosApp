package pruebasIntegracion;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import configuracionPruebas.ConfiguracionGeneralPruebas;

public class PublicarRequerimientoSinInicioDeSesion extends ConfiguracionGeneralPruebas {
	
	private String botonRequerimiento = "//*[@id=\"navigation\"]/ul/li[2]/a";
	private String botonVerPublicarRequerimiento = "//*[@id=\"navigation\"]/ul/li[2]/ul/li/ul/li[1]/a";
	
	@Test
	public void intentarPublicarRequerimientoSinInicioDeSesion() {

		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/sios/home");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		
		driver.findElement(By.xpath(botonRequerimiento)).click();
		
		// Intenta publicar requerimiento
		driver.findElement(By.xpath(botonVerPublicarRequerimiento)).click();

	}

}


