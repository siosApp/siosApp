package pruebasUnitarias;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class SolicitudSinRegistracion extends ConfiguracionGeneralPruebas{

	@Test
	public void realizarSolicitudSinEstarRegistrado() {
		
		
		WebDriver driver = super.configurarSeleniumWebDriver();

		driver.get("http://localhost:4200/sios/home");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		String botonBuscarPath = "/html/body/app-root/app-layout/div/div/app-home/div[1]/div/div/form/div[3]/div/button";
		String SolicitudPath = "/html/body/app-root/app-layout/div/div/app-home/div[2]/div[2]/div[1]/div/div/div[4]/a";
		
		driver.findElement(By.xpath(botonBuscarPath)).click();
		
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(SolicitudPath)).click();
	}

}
