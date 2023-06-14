package com.in28minutes.rest.webservices;

import static org.testng.Assert.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
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
public class StaticLoginTest extends  AbstractTestNGSpringContextTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void f() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user_follow/get/all"),
				HttpMethod.GET, entity, String.class);

		// For what to put as expected response to test, send request at POSTMAN and
		// look at response values
		String expected = "{\"status\":\"OK\",\"message\":\"No users following any comments\",\"code\":200}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		sleep(10);
//		driver.get("http://localhost:4200");
////		System.out.println(driver.getTitle());
//		assertEquals("Mooc app", driver.getTitle());

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
