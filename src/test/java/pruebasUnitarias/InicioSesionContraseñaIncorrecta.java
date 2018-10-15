package pruebasUnitarias;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InicioSesionContraseñaIncorrecta {

	@Test
	public void test() {
		String projectLocation = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", projectLocation+"/chromeDriver/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().maximize();
		
		String usuario = "/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[1]/div/input";
		String contraseña = "/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[2]/div/input";
		String botonIngresarAlSistema = "/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[4]/div/button";
		
		driver.findElement(By.xpath(usuario)).sendKeys("eduardo");
		
		// Ingresa una contraseña incorrecta
		driver.findElement(By.xpath(contraseña)).sendKeys("1234567");
		
		// Intenta ingresar con una contraseña incorrecta
		driver.findElement(By.xpath(botonIngresarAlSistema)).click();	
		
	}

}
