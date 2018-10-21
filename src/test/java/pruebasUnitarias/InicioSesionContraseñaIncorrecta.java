package pruebasUnitarias;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class InicioSesionContraseñaIncorrecta extends ConfiguracionGeneralPruebas {

	@Test
	public void intentarIngresarConContraseñaIncorrecta() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		// Ingresa un usuario correcto
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("eduardo");
		
		// Ingresa una contraseña incorrecta
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("12345");
		
		// Intenta ingresar con una contraseña incorrecta
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//driver.quit();
	
	}

}
