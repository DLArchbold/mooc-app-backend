package com.in28minutes.rest.webservices;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StaticLoginTest extends AbstractChromeWebDriverTest {

//	private WebDriver driver;
//
//	@BeforeTest
//	public void beforeTest() {
//		WebDriverManager.chromedriver().setup();
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		 driver = new ChromeDriver(options);
//	}

	@Test
	public void f() {
		//

//		WebDriverManager.chromedriver().setup();
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.google.com");
//		System.out.println(driver.getTitle());
		assertEquals("Google", driver.getTitle());

	}
	
//	@AfterTest
//	public void afterTest() {
//		driver.quit();
//	}
}
