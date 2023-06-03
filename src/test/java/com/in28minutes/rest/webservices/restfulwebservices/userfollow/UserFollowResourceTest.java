package com.in28minutes.rest.webservices.restfulwebservices.userfollow;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;








import com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RestfulWebServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserFollowResourceTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveStudentCourse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user_follow/get/all"),
                HttpMethod.GET, entity, String.class);

        
        //For what to put as expected response to test, send request at POSTMAN and look at response values
        String expected = "{\"status\":\"OK\",\"message\":\"No users following any comments\",\"code\":200}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void addCourse() {

//        Course course = new Course("Course1", "Spring", "10Steps",
//                List.of("Learn Maven", "Import Project", "First Example", "Second Example"));
//
//        HttpEntity<Course> entity = new HttpEntity<>(course, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/students/Student1/courses"),
//                HttpMethod.POST, entity, String.class);
//
//        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
//
//        assertTrue(actual.contains("/students/Student1/courses/"));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}







//@ExtendWith(SpringExtension.class)
//@WebMvcTest(value = UserFollowResource.class)
//@WithMockUser
//public class UserFollowResourceTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private UserFollowResource userFollowResource;
//
//	@LocalServerPort
//	private int port;
//	HttpHeaders headers = new HttpHeaders();
//
//	TestRestTemplate restTemplate = new TestRestTemplate();
//
//	@Test
//	public void testRetrieveStudentCourse() throws JSONException {
////
////		HttpEntity<String> entity = new HttpEntity<>(null, headers);
////
////		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user_follow/get/all"),
////				HttpMethod.GET, entity, String.class);
////
////		String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";
////
////		JSONAssert.assertEquals(expected, response.getBody(), false);
//	}
//
//	private String createURLWithPort(String uri) {
//		return "http://localhost:" + port + uri;
//	}
//}

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RestfulWebServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserFollowResourceTest {
//
//	@LocalServerPort
//	private int port;
//	HttpHeaders headers = new HttpHeaders();
//
//	TestRestTemplate restTemplate = new TestRestTemplate();
//
//	@Test
//	public void testRetrieveStudentCourse() throws JSONException {
//
//		HttpEntity<String> entity = new HttpEntity<>(null, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user_follow/get/all"),
//				HttpMethod.GET, entity, String.class);
//
//		String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";
//
//		JSONAssert.assertEquals(expected, response.getBody(), false);
//	}
//	private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
//}

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=RestfulWebServicesApplication.class)
////@SpringBootTest
////@DataJpaTest
//public class UserFollowResourceTest {
//
////	@Autowired
////	UserFollowResource userFollowResource = new UserFollowResource();
//
//	@Autowired
//	private TestEntityManager testEntityManager;
//	@Autowired
//	UserFollowRepository userFollowRepository;
//
//	@Test
//	void test() throws Exception {
//
//		userFollowRepository.findAll();
//
//	}
//
//}

////@SpringBootTest
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=RestfulWebServicesApplication.class)
//public class UserFollowResourceTest {
//
//
//
////	@Autowired
////	UserFollowResource userFollowResource = new UserFollowResource();
//	
//	@Autowired
//	UserFollowRepository userFollowRepository;
//	
//	
//	@Test
//	void test() throws Exception {
//
//		userFollowRepository.findAll();
//		
//		
////	userFollowResource.getAllUserFollow();
//		
//		
//
//
//	}
//
//}

//https://stackoverflow.com/questions/48247413/spring-boot-controller-unit-test-failed-to-load-applicationcontext
//@SpringBootTest(classes=RestfulWebServicesApplication.class)
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
////@ContextConfiguration()
////@WebMvcTest(UserFollowResource.class)
//class UserFollowResourceTest {
//
//	@Autowired
//    private MockMvc mvc;
//	
////	@MockBean
////	private UserFollowResource userFollowResource;
////	
//
//	@MockBean
//	RestfulWebServicesApplication restfulWebServicesApplication;
//	
//	@Test
//	void test() throws Exception{
//		
//		
//		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user_follow/get/all");
////		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/application/get-image")
////		            .contentType(MediaType.IMAGE_GIF);
//
//		    MvcResult result = mvc.perform(requestBuilder).andReturn();
//		    System.out.println(result.getResponse());
//		
//		
//	
//
//	}
//
//}

//https://stackoverflow.com/questions/17220432/failed-to-load-applicationcontext-for-junit-test-of-spring-controller
//@RunWith(SpringRunner.class)
//@ContextConfiguration()
//@WebMvcTest(UserFollowResource.class)
//class UserFollowResourceTest {
//
//	@Autowired
//  private MockMvc mvc;
//	
////	@MockBean
////	private UserFollowResource userFollowResource;
////	
//
//	@MockBean
//	RestfulWebServicesApplication restfulWebServicesApplication;
//	
//	@Test
//	void test() throws Exception{
//		
//		
//
//		
//		
//		//For JUnit tests, assert format is (expected, actual), opposite of TestNG
//
//		ApiError apiError = new ApiError(HttpStatus.OK, "No users following any comments");
//
//		ResponseEntity<Object> expectedResponse =  ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
//				.body(apiError);
//		
//		given(this.userFollowResource.getAllUserFollow()).willReturn(expectedResponse);
//		
//		
//		 this.mvc.perform(get("/sboot/vehicle").accept(MediaType.TEXT_PLAIN))
//       .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
//		
//
//		assertEquals( 0, actualResult);
//
//	}
//
//}

////@ExtendWith(SpringExtension.class)
//
////@WebMvcTest(RestfulWebServicesApplication.class)
////@SpringBootTest
//@RunWith(SpringRunner.class)
////@ContextConfiguration(classes=RestfulWebServicesApplication.class)
//@DataJpaTest
//class UserFollowResourceTest {
//
//	
//	@Autowired
//	private UserFollowRepository userFollowRepository;
//	
//	
//	@Test
//	void test() {
//		
//		//For JUnit tests, assert format is (expected, actual), opposite of TestNG
////		UserFollowResource userFollowResouce = new UserFollowResource();
////		ResponseEntity<Object> actualResponse = userFollowResouce.getAllUserFollow();
////		
////		
////		ApiError apiError = new ApiError(HttpStatus.OK, "No users following any comments");
////
////		ResponseEntity<Object> expectedResponse =  ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
////				.body(apiError);
////		
////		assertEquals( expectedResponse , actualResponse );
//		
//		UserFollow a = new UserFollow();
//		a.setUsername("test Username");
//		a.setLessonId(0);
//		List<UserFollow> uf = userFollowRepository.findUserFollowsByEmailByLessonId(a.getUsername() , a.getLessonId());
////		List<UserFollow> listOfAllUserFollows = userFollowRepository.findAll();
//		int actualResult = uf .size();
//		assertEquals( 0, actualResult);
//
//	}
//
//}
