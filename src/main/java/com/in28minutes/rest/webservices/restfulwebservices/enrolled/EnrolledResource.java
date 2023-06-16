package com.in28minutes.rest.webservices.restfulwebservices.enrolled;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.in28minutes.rest.webservices.restfulwebservices.ApiError;
import com.in28minutes.rest.webservices.restfulwebservices.course.Course;
import com.in28minutes.rest.webservices.restfulwebservices.course.CourseRepository;

@RestController
//@CrossOrigin
public class EnrolledResource {

	@Autowired
	private EnrolledRepository enrolledRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/enrolled/get/all")
	public ResponseEntity<Object> getAllEnrolled() {
		// if there's a @PathVariable long enrolledId in method parameter
		// Then it must be part of URI variable also like {enrolledId}/enrolled
		// else will have error
//		return commentRepository.findByUsername(username);
		List<Enrolled> listOfAllEnrolleds = enrolledRepository.findAll();
		if (listOfAllEnrolleds.size() > 0) {
			// Return details of all users
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(listOfAllEnrolleds);

//					new ResponseEntity<>(listOfAllEnrolleds , HttpStatus.OK);

		} else {
			// List of all users are empty
//			return ResponseEntity.noContent().build();
			ApiError apiError = new ApiError(HttpStatus.OK, "No enrolleds");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);

		}

	}

	@GetMapping("/enrolled/get/{enrolledId}")
	public ResponseEntity<Object> getEnrolledUsingID(@PathVariable long enrolledId) {
//		return  enrolledRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(enrolledId);

		Enrolled enrolled = findEnrolled(enrolledId);
		if (enrolled != null) {

			// Return enrolled
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolled);

		} else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Enrolled not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
	}

	@GetMapping("/enrolled/get/getByCourseId/{courseId}")
	public ResponseEntity<Object> getEnrolledUsingCourseId(@PathVariable long courseId) {
//			return  enrolledRepository.findById(id).get();

//			List<Long> tempList = new ArrayList<Long>();
//			tempList.add(enrolledId);

		List<Enrolled> enrolled = enrolledRepository.findByCourseId(courseId);
		if (enrolled != null) {

			// Return enrolled
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolled);

		} else {
			// User not found
			// Implementation refer to #7
//				https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Enrolled not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		enrolledRepository.findby
//		enrolledRepository.find
//		return enrolledRepository.findAllById(tempList);
	}

	@GetMapping("/enrolled/get/byCourseIdByUsername/{courseId}/{username}")
	public ResponseEntity<Object> getEnrolledByCourseIdByUsername(@PathVariable long courseId,
			@PathVariable String username) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}

		Enrolled enrolledCheck = new Enrolled();
		enrolledCheck.setUsername(username);
		enrolledCheck.setCourseId(courseId);

		List<Enrolled> enrolled = enrolledRepository.findByCourseIdAndUsername(username, courseId);

		if (enrolled != null) {

			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolled);
		} else {
			// Enrolled not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Enrolled not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);

		}

//		return ResponseEntity.noContent().build();
	}

	@GetMapping("/enrolled/get/byUsername/{username}")
	public ResponseEntity<Object> getEnrolledByUsername(@PathVariable String username) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}

		Enrolled enrolledCheck = new Enrolled();
		enrolledCheck.setUsername(username);

		List<Enrolled> enrolled = enrolledRepository.findByUsername(username);

		if (enrolled != null) {

			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolled);
		} else {
			// Enrolled not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Enrolled not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);

		}

//		return ResponseEntity.noContent().build();
	}

	@GetMapping("/enrolled/get/byUsername/{username}/returnCourseDetails")
	public ResponseEntity<Object> getEnrolledCourseDetails(@PathVariable String username) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}

		Enrolled enrolledCheck = new Enrolled();
		enrolledCheck.setUsername(username);

		List<Enrolled> enrolled = enrolledRepository.findByUsername(username);
		if (enrolled != null) {
			List<Course> enrolledCouseDetails = new ArrayList<Course>();
			for (Enrolled i : enrolled) {
				enrolledCouseDetails.add(courseRepository.findCourseById(i.getCourseId()));
			}

//			for(int j = 0; j<enrolledCouseDetails.size(); j++) {
//				System.out.println("test: "+ enrolledCouseDetails.get(j).getTitle());	
//			}

			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(enrolledCouseDetails);

		} else {
			// Enrolled not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Enrolled not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);

		}

//		return ResponseEntity.noContent().build();
	}

//	
//	@GetMapping("/enrolled/get/usingEnrolledId/{enrolledId}")
//	public ResponseEntity<Object> getEnrolledsUsingEnrolledId(@PathVariable long enrolledId) {
////		return  enrolledRepository.findById(id).get();
//
////		List<Long> tempList = new ArrayList<Long>();
////		tempList.add(enrolledId);
//		Enrolled a = new Enrolled();
//		a.setEnrolledId(enrolledId);
//		
//		List<Enrolled> enrolleds = enrolledRepository.findEnrolledsByEnrolledId(enrolledId);
//		if(enrolleds.size() >0) {
//			
//			// Return list of Enrolleds related to a enrolled
//			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolleds);
//			
//		}else {
//			// No enrolleds related to a enrolled
//			// Implementation refer to #7
////			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
//			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No enrolleds associated with enrolled");
//
//			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
//		}
//		
//
////		return ResponseEntity.noContent().build();
//
////		return toReturn;
//
////		enrolledRepository.findby
////		enrolledRepository.find
////		return enrolledRepository.findAllById(tempList);
//	}
//	

	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/enrolled/delete/{enrolledId}")
	public ResponseEntity<Object> deleteEnrolled(@PathVariable long enrolledId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}

		Enrolled enrolled = findEnrolled(enrolledId);

		if (enrolled != null) {

			enrolledRepository.deleteById(enrolledId);

			// Return and display successfully deleted Enrolled
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolled);
		} else {

			// Notify that no such Enrolled exists
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Enrolled to delete");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);

		}

//		return ResponseEntity.noContent().build();
	}

	@PutMapping("/enrolled/update")
	public ResponseEntity<Object> updateEnrolled(@RequestBody Enrolled enrolled) {
//		Comment commentUpdated = commentService.save(comment);

//		comment.setUsername(username);

		if (enrolledRepository.findById(enrolled.getId()).isPresent()) {
			Enrolled enrolledUpdated = enrolledRepository.save(enrolled);
//			return new ResponseEntity<Comment>(comment, HttpStatus.OK);

			// Return and display newly updated Enrolled
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolledUpdated);
		} else {

			// Notify of the updating of a nonexistent Enrolled
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Enrolled to be updated");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);

		}

	}

	@PostMapping("/enrolled/create")
	public ResponseEntity<Object> createEnrolled(@RequestBody Enrolled enrolled) {

//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);

//		comment.setId(-1L);

//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);

		if (enrolledRepository.findById(enrolled.getId()).isPresent()) {
			System.out.println("Enrolled already exists: " + enrolledRepository.findById(enrolled.getId()));
//			toReturn.add(f);

			// Return response that notifies that Enrolled already exists
			ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Enrolled already exists");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		} else {
			// Notify of successful addition of new enrolled
			Enrolled createdEnrolled = enrolledRepository.save(enrolled);
			// Return and display newly created Enrolled
			return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin")
					.body(createdEnrolled);
		}

		// Location
		// Get current resouce url
		// {id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/enrolled/get/{id}").buildAndExpand(createdEnrolled.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();

	}

//	protected Enrolled findEnrolledsByUsernameByEnrolledIdAndCommentId(Enrolled enrolled) {
//		List<Enrolled> temp = new ArrayList<Enrolled>();
//		temp = enrolledRepository.findAll();
//
//	
//		
////		List<Enrolled> toReturn = new ArrayList<Enrolled>();
//		for (Enrolled f : temp) {
//
//			if (f.getUsername().equals(enrolled.getUsername())
//					&& f.getEnrolledId() == enrolled.getEnrolledId()
//					&& f.getCommentId() == enrolled.getCommentId()) {
//				return f;
//			}
//		}
//
//		return null;
//
//	}

//	protected List<Enrolled> findEnrolledsByUsernameByEnrolledId(Enrolled enrolled) {
//		List<Enrolled> temp = new ArrayList<Enrolled>();
////		temp = enrolledRepository.findAll();
//
//		List<Enrolled> uf = enrolledRepository.findEnrolledsByEmailByEnrolledId(enrolled.getUsername() , enrolled.getEnrolledId());
//		return uf;
////		List<Enrolled> toReturn = new ArrayList<Enrolled>();
////		for (Enrolled f : temp) {
////
////			if (f.getUsername().equals(enrolled.getUsername())
////					&& f.getEnrolledId() == enrolled.getEnrolledId()) {
////				return f;
////			}
////		}
////
////		return null;
//
//	}

	protected Enrolled findEnrolled(long enrolledId) {
		List<Enrolled> temp = new ArrayList<Enrolled>();
		temp = enrolledRepository.findAll();

//		List<Enrolled> toReturn = new ArrayList<Enrolled>();
		for (Enrolled f : temp) {

			if (f.getId() == enrolledId) {
				return f;
			}
		}

		return null;

	}
}

//@GetMapping("/enrolled/get/authenticate/{email}/{password}")
//public ResponseEntity<Object> authenticateEnrolled(@PathVariable String email,
//		@PathVariable String password) {
////	return  enrolledRepository.findById(id).get();
//
////	List<Long> tempList = new ArrayList<Long>();
////	tempList.add(enrolledId);
//	Enrolled a = new Enrolled();
//	a.setEmail(email);
//	Enrolled enrolled = findUser(a);
//	if(enrolled.getPassword().equals(password)) {
//		
//		// Return user detail
//		return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(enrolled);
//		
//	}else {
//		// User not found
//		// Implementation refer to #7
////		https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
//		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Incorrect email or password");
//
//		return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
//	}
//	
//
////	return ResponseEntity.noContent().build();
//
////	return toReturn;
//
////	enrolledRepository.findby
////	enrolledRepository.find
////	return enrolledRepository.findAllById(tempList);
//}
