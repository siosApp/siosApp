package selenium;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {

	@Test
	public void test() {
		String projectLocation = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", projectLocation+"/chromeDriver/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[1]/div/input")).sendKeys("test selenium username");
		driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[2]/div/input")).sendKeys("test selenium password");
		driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[3]/div/div/label")).click();
		driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[1]/div[2]/form/div[4]/div/button")).click();
		
		assertTrue(driver.getPageSource().contains("No tenés una cuenta?"));
		driver.quit();
		
	}
 
}
