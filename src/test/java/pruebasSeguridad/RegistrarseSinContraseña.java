package pruebasSeguridad;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class RegistrarseSinContraseña extends ConfiguracionGeneralPruebas {

	@Test
	public void testRegistrarseSinContraseña() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();

		driver.get("http://localhost:4200/registracion");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		String UsernamePath = "//*[@id=\"field-2\"]";
		String MailPath = "//*[@id=\"field-6\"]";
		String registrarsePath = "/html/body/app-root/app-registracion/div[3]/div[1]/div[1]/div[2]/form/div[4]/div/button";

		driver.findElement(By.xpath(UsernamePath)).sendKeys("dante");
		
		driver.findElement(By.xpath(MailPath)).sendKeys("dante@gmail.com");
		
		//selecciona el boton registrarse
		driver.findElement(By.xpath(registrarsePath)).click();
		
	}

}
