package com.in28minutes.rest.webservices;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractChromeWebDriverTest {

	protected WebDriver driver;

	public AbstractChromeWebDriverTest() {
		super();
	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}