package pruebasIntegracion;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import configuracionPruebas.ConfiguracionGeneralPruebas;

public class IntentarCambiarContraseñaSinVerificar extends ConfiguracionGeneralPruebas {
	
	private String botonPerfil = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[2]/a";
	private String botonVerPerfil = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[2]/ul/li[2]/a[1]";
	private String campoCOntraseña = "/html/body/app-root/app-layout/div/div/app-perfil/div[1]/div[2]/div/div/div/div/form/div[7]/div[1]/div/input";
	
	@Test
	public void intentarCambiarContraseñaSinVerificar() {

		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("eduardo");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(botonPerfil)).click();
		
		driver.findElement(By.xpath(botonVerPerfil)).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Cambia la contraseña sin cambiar la verificacion de esta.
		driver.findElement(By.xpath(campoCOntraseña)).sendKeys("12345");
		
	}

}


