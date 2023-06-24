package com.in28minutes.rest.webservices;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractChromeWebDriverTest extends AbstractTestNGSpringContextTests {

	protected WebDriver driver;

	public AbstractChromeWebDriverTest() {
		super();
	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		//CURRENT OPTIONS ABLE TO MAKE JENKINS BUILD SUCCESSFULLY 
		//options.addArguments("--crash-dumps-dir=/tmp"); WAS THE PROBLEM AFTER TESTING OUT FOR TENS OF HOURS
		// IN SEE https://onedrive.live.com/view.aspx?resid=B5C7479FF28A6A32%21406&id=documents&wd=target%28Project%20related%20notes.one%7C2D90FC29-71EE-4D14-9003-4633C7E46871%2F%28pt-2%5C%29%20DevOps%20Project%20-%20CICD%20with%20Git%20Jenkins%7C96251DD3-8CE4-44EF-88D3-71AE9252D32D%2F%29
		//onenote:https://d.docs.live.net/b5c7479ff28a6a32/Documents/Work%20related/Project%20related%20notes.one#(pt-2)%20DevOps%20Project%20-%20CICD%20with%20Git%20Jenkins&section-id={2D90FC29-71EE-4D14-9003-4633C7E46871}&page-id={96251DD3-8CE4-44EF-88D3-71AE9252D32D}&object-id={1616DB7D-B74C-0E1A-1CF6-BC731563DAE4}&8C
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--crash-dumps-dir=/tmp");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--remote-debugging-port=9222");
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