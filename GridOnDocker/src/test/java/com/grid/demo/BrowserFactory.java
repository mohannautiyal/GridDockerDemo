package com.grid.demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	WebDriver driver;
	public  static WebDriver getBrowser(String browserName) {
		WebDriver driver=null;
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();			
            driver = new ChromeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
			
		}
			return driver;
		
	}

	public static WebDriver getTdriver() {
		return tdriver.get();
	}

	public static void setTdriver(WebDriver driver) {
		tdriver.set(driver);
	}
	
	
	
	public void setUpBrowser(String browsername) throws MalformedURLException {
	      
		DesiredCapabilities cap = new DesiredCapabilities();
		String hub="http://192.168.99.100:4444/wd/hub";
		if(browsername.equals("chrome")){
			System.out.println("Setting browser chrome");
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			ChromeOptions copt = new ChromeOptions();
			copt.merge(cap);
			}
		else {
			cap.setBrowserName("firefox");
			FirefoxOptions fopt = new FirefoxOptions();
			fopt.merge(cap);
		}
	
		//Chrome Options
				
		driver = new RemoteWebDriver(new URL(hub),cap);
		driver.get("https://freecrm.co.in/");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		//driver.quit();
	}


}
