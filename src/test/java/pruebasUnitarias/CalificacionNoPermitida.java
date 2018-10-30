package pruebasUnitarias;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import configuracionPruebas.ConfiguracionGeneralPruebas;

public class CalificacionNoPermitida extends ConfiguracionGeneralPruebas {
	
	private String botonSolicitudesNuevas = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[1]/a";
	private String botonVerSolicitudes = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[1]/ul/li[2]/div/a";
	private String botonFinalizarSolicitud = "/html/body/app-root/app-layout/div/div/app-ver-solicitud/div[2]/div[1]/div[3]/div/div[4]/div/a";

	@Test
	public void intentarCalificarSinHaberFinalizadoElTRabajo() {

		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("seba.lruiz");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("Ratones12");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(botonSolicitudesNuevas)).click();
		
		driver.findElement(By.xpath(botonVerSolicitudes)).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(botonFinalizarSolicitud)).click();
	
	}

}

