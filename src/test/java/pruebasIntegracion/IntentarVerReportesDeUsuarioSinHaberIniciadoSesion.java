package pruebasIntegracion;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import configuracionPruebas.ConfiguracionGeneralPruebas;

public class IntentarVerReportesDeUsuarioSinHaberIniciadoSesion extends ConfiguracionGeneralPruebas {

	@Test
	public void testIntentarVerReportesDeUsuarioSinHaberIniciadoSesion() {
		
		WebDriver driver = super.configurarSeleniumWebDriver();
		driver.get("http://localhost:4200/home");
		driver.manage().window().setSize(new Dimension(1440, 900));

		String dashboardPath = "//*[@id=\"navigation\"]/ul/li[5]/a";
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(dashboardPath)).click();
	}

}