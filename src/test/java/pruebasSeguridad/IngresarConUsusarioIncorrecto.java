

package pruebasSeguridad;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class IngresarConUsusarioIncorrecto extends ConfiguracionGeneralPruebas {

	@Test
	public void testIngresarConUsusarioIncorrecto() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		// Ingresa un usuario incorrecto
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("eduard");
		
		// Ingresa una contraseña correcta
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		// Intenta ingresar con un usuario incorrecto
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
	}

}
