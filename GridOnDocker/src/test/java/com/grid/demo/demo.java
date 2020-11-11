package com.grid.demo;

import org.openqa.selenium.WebDriver;

public class demo {

	
	public static void main(String[] args) {
		WebDriver bdriver =BrowserFactory.getBrowser("chrome");
	    BrowserFactory.setTdriver(bdriver);
	    WebDriver Tdriver= BrowserFactory.getTdriver();	
	    
	    Tdriver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	    System.out.println(Tdriver.getTitle());
	    Tdriver.close();
	}
}
