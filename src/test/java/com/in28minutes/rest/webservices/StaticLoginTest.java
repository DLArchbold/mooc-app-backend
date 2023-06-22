package com.in28minutes.rest.webservices;

import static org.testng.Assert.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication;

import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RestfulWebServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT sets server.port in application.properties to 0
//webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT just uses the value you set in application.properties.
public class StaticLoginTest extends AbstractChromeWebDriverTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void f() {

//		HttpEntity<String> entity = new HttpEntity<>(null, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user_follow/get/all"),
//				HttpMethod.GET, entity, String.class);
//
//		// For what to put as expected response to test, send request at POSTMAN and
//		// look at response values
//		String expected = "{\"status\":\"OK\",\"message\":\"No users following any comments\",\"code\":200}";
//
//		try {
//			JSONAssert.assertEquals(expected, response.getBody(), false);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		sleep(10);
		driver.get("http://localhost:4200");
//		System.out.println(driver.getTitle());
//		sleep(5);

		WebElement buttonElement = driver.findElement(By.tagName("button"));
		buttonElement.click();
		sleep(60);

		// Switch login page now
		String parentWindowHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {

			if (!winHandle.equals(parentWindowHandle)) {
				//Switching to Keycloak login popup page
				driver.switchTo().window(winHandle); // Here yor switching control to child window so that you can
														// perform action on child window
				System.out.println("Title of the new window: " + driver.getTitle());
				// code to do something on new window
				WebElement usernameElement = driver.findElement(By.id("username"));
				usernameElement .sendKeys("student1@gmail.com");
				sleep(2);
				WebElement passwordElement = driver.findElement(By.id("password"));
				passwordElement .sendKeys("student1");
				sleep(2);
				WebElement signInButton = driver.findElement(By.cssSelector("#kc-login"));
				signInButton.click();
				
				sleep(5);

//				System.out.println("Closing the new window...");
//				driver.close();
			}

		}

		driver.switchTo().window(parentWindowHandle);
		System.out.println("welcome-page-title : " + driver.getTitle());
		WebElement welcomeMessage = driver.findElement(By.id("welcome-message"));
		System.out.println("welcomeMessage.getText():" + welcomeMessage.getText()); //welcomeMessage:[[ChromeDriver: chrome on WINDOWS (9c9774a520781adb9f870d34d047f6a0)] -> id: welcome-message]
		
		//Write simple login test to ensure login welcome message is as intended
		assertEquals("Welcome student1@gmail.com. View all instructor comments here.", welcomeMessage.getText());
		
		
		
		
		
		sleep(30);
//		
//		assertEquals("Mooc app", driver.getTitle());

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
