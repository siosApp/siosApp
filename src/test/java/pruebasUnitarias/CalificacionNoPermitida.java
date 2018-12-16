
package pruebasUnitarias;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import configuracionPruebas.ConfiguracionGeneralPruebas;
import org.openqa.selenium.JavascriptExecutor;

public class CalificacionNoPermitida extends ConfiguracionGeneralPruebas {
	
	private String botonSolicitudesNuevas = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[2]/a/img";
	private String botonVerMisSolicitudes = "//*[@id=\"topnav\"]/div[1]/div/div[2]/ul/li[2]/ul/li[2]/a[2]";
	private String botonFinalizarSolicitud = "//*[@id=\"calificarModal\"]";

	
	
	@Test
	public void intentarCalificarSinHaberFinalizadoElTRabajo() {

		WebDriver driver = super.configurarSeleniumWebDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		driver.get("http://localhost:4200/login");
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		driver.findElement(By.xpath(super.getUsuarioXpath())).sendKeys("eduardo");
		
		driver.findElement(By.xpath(super.getContraseñaXpath())).sendKeys("123456");
		
		driver.findElement(By.xpath(super.getBotonMantenerSesionActivaXpath())).click();
		
		driver.findElement(By.xpath(super.getBotonIngresarAlSistemaXpath())).click();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(botonSolicitudesNuevas)).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(botonVerMisSolicitudes)).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		WebElement element = driver.findElement(By.xpath(botonFinalizarSolicitud));
		js.executeScript("arguments[0].style='display: block;'", element);
	}

}
