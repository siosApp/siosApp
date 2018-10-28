package pruebasSeguridad;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class verListaInformesSinPrivilegios extends ConfiguracionGeneralPruebas {

	@Test
	public void testIngresarAConfiguracionMedianteURL() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		// Ingreso al sistema con un usuario que no es administrador
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("usuarioprueba");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		// Intento acceder a los reportes del administrador a través de la URL
		driver.get("http://localhost:4200/sios/dashboard");		
				
	}

}
