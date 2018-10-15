package pruebasUnitarias;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistracionConUsuarioExistente {

	@Test
	public void test() {
		String projectLocation = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", projectLocation+"/chromeDriver/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200/registracion");
		driver.manage().window().maximize();
		
		String UsernamePath = "//*[@id=\"field-2\"]";
		String MailPath = "//*[@id=\"field-6\"]";
		String PasswordPath = "/html/body/app-root/app-registracion/div[3]/div[1]/div[2]/form/div[3]/div[1]/div/div/input";
		String ReingresePasswordPath = "/html/body/app-root/app-registracion/div[3]/div[1]/div[2]/form/div[3]/div[2]/div/div/input";
		String botonCrearUsuarioPath = "/html/body/app-root/app-registracion/div[3]/div[1]/div[2]/form/div[4]/div/button";
		
		// Intenta registrar a un usuario con un mail existente
		driver.findElement(By.xpath(UsernamePath)).sendKeys("jorge");
		
		// Este mail ya existe en el sistema
		driver.findElement(By.xpath(MailPath)).sendKeys("jorge@gmail.com");
		
		driver.findElement(By.xpath(PasswordPath)).sendKeys("1234567");
		
		driver.findElement(By.xpath(ReingresePasswordPath)).sendKeys("1234567");
		
		driver.findElement(By.xpath(botonCrearUsuarioPath)).click();
		
		// Espera hasta que se cargue la nueva página con el mensaje de éxito
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Intenta obtener el mensaje de éxito
		assertTrue(driver.getPageSource().contains("Se ha registrado con éxito"));
		
	}

}
