package pruebasSeguridad;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CerrarSesionAutomaticamente extends PruebaSeguridad {

	@Test
	public void testCerrarSesionAutomaticamente() {
	
		WebDriver driver = super.configurarSeleniumWebDriver();
		
		driver.get("http://localhost:4200/login");
		driver.manage().window().maximize();
		
		String botonPerfil = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[2]/ul/li/a";
		String usuario = "/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[1]/div/input";
		String contraseña = "/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[2]/div/input";
		String botonMantenerSesionActiva = "//*[@id=\"checkbox-signup\"]";
		String botonIngresarAlSistema = "/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[4]/div/button";
		
		// Ingreso al sistema con un usuario que no es administrador
		driver.findElement(By.xpath(usuario)).sendKeys("dante");
		
		driver.findElement(By.xpath(contraseña)).sendKeys("dante12345");
		
		driver.findElement(By.xpath(botonMantenerSesionActiva)).click();
		
		driver.findElement(By.xpath(botonIngresarAlSistema)).click();
		
		// espero 10 segundos para que pueda cargar la nueva pagina web
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Intento seleccionar el boton para ingresar al perfil
		driver.findElement(By.xpath(botonPerfil)).click();
		
		
	}

}
