package com.grid.demo;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebAppTestsFirefox {

	
	
ThreadLocal<WebDriver> threaddriver = new ThreadLocal();
//String hub="http://192.168.99.1:4444/wd/hub";
String hub="http://192.168.99.100:4444/wd/hub";

	@BeforeMethod
	public void Setup() throws MalformedURLException {
		
		WebDriverManager.firefoxdriver().setup();
		DesiredCapabilities cap =  new DesiredCapabilities();
		
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.ANY);
		FirefoxOptions fopt = new FirefoxOptions();
		//fopt.setBinary("geckodriver.exe");
		
		fopt.merge(cap);
		//fopt.merge(cap);
		
		 threaddriver.set(new RemoteWebDriver(new URL(hub),cap));
		 WebDriver driver = threaddriver.get();
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Launching Application.."+ Thread.currentThread().getName());
        
		
	}
	
	@Test
	public void verifyAppLaunch() {
	 WebDriver driver = threaddriver.get();

	 List<WebElement> logoelem=	driver.findElements(By.id("divLogo"));	 
	 assertTrue(logoelem.size()>0, "Logo not present");
	 System.out.println("Logo verification passed");
	}
	
	
	@Test
	public void verifyAppLogin() {
	 WebDriver driver =  threaddriver.get();

	 	driver.findElement(By.id("txtUsername")).sendKeys("Admin");	 
	 	driver.findElement(By.id("txtPassword")).sendKeys("admin123");	 
	 	driver.findElement(By.id("btnLogin")).click();	 

	 int elem=driver.findElements(By.xpath("//h1[contains(text(),'Dashboard')]")).size();
	 assertTrue(elem>0, "Login failed");
	 System.out.println("Login test successfull");
	}
	
	@AfterMethod
	public void quit() {
		 WebDriver driver =  threaddriver.get();

		System.out.println("Closing Browser...");
		driver.quit();
	}
}
