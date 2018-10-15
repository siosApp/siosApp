package pruebasUnitarias;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SolicitudSinRegistracion {

	@Test
	public void realizarSolicitudSinEstarRegistrado() {
		
		String projectLocation = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", projectLocation+"/chromeDriver/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200/sios/home");
		driver.manage().window().maximize();
		
		String botonBuscarPath = "/html/body/app-root/app-layout/div/div/app-home/div[1]/div/div/form/div[3]/div/button";
		String oferentePath = "/html/body/app-root/app-layout/div/div/app-home/div[2]/div/div/div/div/button";
		
		driver.findElement(By.xpath(botonBuscarPath)).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(oferentePath)).click();
	}

}
