package pruebasSeguridad;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PruebaSeguridad {
	
	private String usuarioXpath;
	private String contraseñaXpath;
	private String botonMantenerSesionActivaXpath;
	private String botonIngresarAlSistemaXpath;

	public PruebaSeguridad() {
		setUsuarioXpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[1]/div/input");
		setContraseñaXpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[2]/div/input");
		setBotonMantenerSesionActivaXpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[3]/div/div/label");
		setBotonIngresarAlSistemaXpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[4]/div/button");
		
	}

	public WebDriver configurarSeleniumWebDriver() {
		
		String projectLocation = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", projectLocation+"/chromeDriver/chromedriver");

		WebDriver driver = new ChromeDriver();
		
		return driver;
	}

	public String getUsuarioXpath() {
		return usuarioXpath;
	}

	public void setUsuarioXpath(String usuarioXpath) {
		this.usuarioXpath = usuarioXpath;
	}

	public String getContraseñaXpath() {
		return contraseñaXpath;
	}

	public void setContraseñaXpath(String contraseñaXpath) {
		this.contraseñaXpath = contraseñaXpath;
	}

	public String getBotonMantenerSesionActivaXpath() {
		return botonMantenerSesionActivaXpath;
	}

	public void setBotonMantenerSesionActivaXpath(String botonMantenerSesionActivaXpath) {
		this.botonMantenerSesionActivaXpath = botonMantenerSesionActivaXpath;
	}

	public String getBotonIngresarAlSistemaXpath() {
		return botonIngresarAlSistemaXpath;
	}

	public void setBotonIngresarAlSistemaXpath(String botonIngresarAlSistemaXpath) {
		this.botonIngresarAlSistemaXpath = botonIngresarAlSistemaXpath;
	}

}
