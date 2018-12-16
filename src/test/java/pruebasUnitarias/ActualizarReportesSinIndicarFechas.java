package pruebasUnitarias;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class ActualizarReportesSinIndicarFechas extends ConfiguracionGeneralPruebas{

	@Test
	public void testActualizarReportesSinIndicarFechas() {
		
		
		WebDriver driver = super.configurarSeleniumWebDriver();

		String botonReportesPath = "//*[@id=\"navigation\"]/ul/li[3]/a";
		String rubrosMasDemandadosPath = "//*[@id=\"navigation\"]/ul/li[3]/ul/li/ul/li/a[3]";
		String botonActualizarPath = "/html/body/app-root/app-layout/div/div/app-rubros-mas-demandados/form/div/div/div/div/div[2]/div[3]/button";

		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("eduardo");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(botonReportesPath)).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		
		driver.findElement(By.xpath(rubrosMasDemandadosPath)).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath(botonActualizarPath)).click();
	}

}
