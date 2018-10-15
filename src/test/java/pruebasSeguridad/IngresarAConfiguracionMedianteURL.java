package pruebasSeguridad;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IngresarAConfiguracionMedianteURL extends PruebaSeguridad {

	@Test
	public void testIngresarAConfiguracionMedianteURL() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().maximize();
		
		// Ingreso al sistema con un usuario que no es administrador
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("usuarioprueba");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		// Intento acceder a el ABM Tipo Rubro de la configuración a través de la URL
		driver.get("http://localhost:4200/sios/tipoRubro");		
		
		driver.quit();
		
	}

}
