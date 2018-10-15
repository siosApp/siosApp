package pruebasSeguridad;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IngresoConfiguracionDelSistemaSinPermiso extends PruebaSeguridad {

	@Test
	public void testIngresarAlSistemaSinPermiso() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();
		
		
		List<String> navigationItemsTexts = new ArrayList<>();

		driver.get("http://localhost:4200/login");
		driver.manage().window().maximize();
		
		// Ingresa al sistema con un usuario que no es administrador
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("usuarioprueba");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		// Espera hasta que se cargue la nueva página con el mensaje de éxito
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Crea una lista con todos los textos del navigation
		List<WebElement> navigationItems = driver.findElements(By.cssSelector("#navigation li"));
		for (WebElement webElement : navigationItems) {
			navigationItemsTexts.add(webElement.findElement(By.tagName("a")).getText());
		}
		
		// comprueba que exista configuración dentro de la lista de textos
		assertTrue(navigationItemsTexts.contains("Configuración"));
		driver.quit();
		
	}

}
