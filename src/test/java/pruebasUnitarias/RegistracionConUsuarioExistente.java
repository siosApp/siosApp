package pruebasUnitarias;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class RegistracionConUsuarioExistente extends ConfiguracionGeneralPruebas {

	@Test
	public void intentarRegistroConUsuarioExistente() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();

		driver.get("http://localhost:4200/registracion");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		String UsernamePath = "//*[@id=\"field-2\"]";
		String MailPath = "//*[@id=\"field-6\"]";
		String PasswordPath = "/html/body/app-root/app-registracion/div[3]/div/div[1]/div[2]/form/div[3]/div[1]/div/div/input";
		String ReingresePasswordPath = "/html/body/app-root/app-registracion/div[3]/div/div[1]/div[2]/form/div[3]/div[2]/div/div/input";
		
		// Intenta registrar a un usuario con un mail existente
		driver.findElement(By.xpath(UsernamePath)).sendKeys("eduardo");
		
		// Este mail ya existe en el sistema
		driver.findElement(By.xpath(MailPath)).sendKeys("eduardo@gmail.com");
		
		driver.findElement(By.xpath(PasswordPath)).sendKeys("1234567");
		
		driver.findElement(By.xpath(ReingresePasswordPath)).sendKeys("1234567");
		
	}

}
