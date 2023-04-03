package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.ApiError;

@RestController
@CrossOrigin
public class ApplicationUserResource {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@GetMapping("/application_user/get/all")
	public ResponseEntity<Object> getAllApplicationUser() {
		// if there's a @PathVariable long lessonId in method parameter
		// Then it must be part of URI variable also like {lessonId}/application_user
		// else will have error
//		return commentRepository.findByUsername(username);
		List<ApplicationUser> listOfAllApplicationUsers = applicationUserRepository.findAll();
		if (listOfAllApplicationUsers.size() > 0) {
			// Return details of all users
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(listOfAllApplicationUsers);

//					new ResponseEntity<>(listOfAllApplicationUsers , HttpStatus.OK);

		} else {
			// List of all users are empty
//			return ResponseEntity.noContent().build();
			ApiError apiError = new ApiError(HttpStatus.OK, "No users");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	@GetMapping("/application_user/get/{applicationUserId}")
	public ResponseEntity<Object> getApplicationUserUsingID(@PathVariable long applicationUserId) {
//		return  applicationUserRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(applicationUserId);


		ApplicationUser applicationUser = findUser(applicationUserId);
		if(applicationUser!=null) {
			
			// Return user detail
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(applicationUser);
			
		}else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		
	

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		applicationUserRepository.findby
//		applicationUserRepository.find
//		return applicationUserRepository.findAllById(tempList);
	}

	
	@GetMapping("/application_user/get/usingEmail/{email}")
	public ResponseEntity<Object> getApplicationUserUsingEmail(@PathVariable String email) {
//		return  applicationUserRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(applicationUserId);
		ApplicationUser a = new ApplicationUser();
		a.setEmail(email);
		ApplicationUser applicationUser = findUser(a);
		if(applicationUser!=null) {
			
			// Return user detail
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(applicationUser);
			
		}else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		applicationUserRepository.findby
//		applicationUserRepository.find
//		return applicationUserRepository.findAllById(tempList);
	}
	
	
	@GetMapping("/application_user/get/authenticate/{email}/{password}")
	public ResponseEntity<Object> authenticateApplicationUser(@PathVariable String email,
			@PathVariable String password) {
//		return  applicationUserRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(applicationUserId);
		ApplicationUser a = new ApplicationUser();
		a.setEmail(email);
		ApplicationUser applicationUser = findUser(a);
		if(applicationUser.getPassword().equals(password)) {
			
			// Return user detail
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(applicationUser);
			
		}else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Incorrect email or password");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		applicationUserRepository.findby
//		applicationUserRepository.find
//		return applicationUserRepository.findAllById(tempList);
	}
	
	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/application_user/delete/{applicationUserId}")
	public ResponseEntity<Object> deleteApplicationUser(@PathVariable long applicationUserId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		
		ApplicationUser applicationUser = findUser(applicationUserId);
		
		if (applicationUser!= null) {
			
			applicationUserRepository.deleteById(applicationUserId);
			
			//Return and display successfully deleted ApplicationUser
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(applicationUser);
		}else {
			
			//Notify that no such ApplicationUser exists
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such user to delete");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
			
		}
		
		

//		return ResponseEntity.noContent().build();
	}

	
	@PutMapping("/application_user/update")
	public ResponseEntity<Object> updateApplicationUser(
			@RequestBody ApplicationUser applicationUser) {
//		Comment commentUpdated = commentService.save(comment);

//		comment.setUsername(username);

		if (findUser(applicationUser) != null) {
			ApplicationUser applicationUserUpdated = applicationUserRepository.save(applicationUser);
//			return new ResponseEntity<Comment>(comment, HttpStatus.OK);

			// Return and display newly updated ApplicationUser
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(applicationUserUpdated);
		} else {
			
			//Notify of the updating of a nonexistent user 
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such user to be updated");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	@PostMapping("/application_user/create")
	public ResponseEntity<Object> createApplicationUser(@RequestBody ApplicationUser applicationUser) {

//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);

//		comment.setId(-1L);

//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);

		if (findUser(applicationUser) != null) {
			System.out.println("User already exists");
//			toReturn.add(f);

			// Return response that notifies that ApplicationUser already exists
			ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "User already exists");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
		} else {
			// Notify of successful addition of new user
			ApplicationUser createdApplicationUser = applicationUserRepository.save(applicationUser);
			// Return and display newly created ApplicationUser
			return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin")
					.body(createdApplicationUser);
		}

		// Location
		// Get current resouce url
		// {id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/application_user/get/{id}").buildAndExpand(createdApplicationUser.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();

	}

	protected ApplicationUser findUser(ApplicationUser applicationUser) {
		List<ApplicationUser> temp = new ArrayList<ApplicationUser>();
		temp = applicationUserRepository.findAll();

//		List<ApplicationUser> toReturn = new ArrayList<ApplicationUser>();
		for (ApplicationUser f : temp) {

			if (f.getEmail().equals(applicationUser.getEmail())) {
				return f;
			}
		}

		return null;

	}

	protected ApplicationUser findUser(long applicationUserId) {
		List<ApplicationUser> temp = new ArrayList<ApplicationUser>();
		temp = applicationUserRepository.findAll();

//		List<ApplicationUser> toReturn = new ArrayList<ApplicationUser>();
		for (ApplicationUser f : temp) {

			if (f.getId() == applicationUserId) {
				return f;
			}
		}

		return null;

	}
}
