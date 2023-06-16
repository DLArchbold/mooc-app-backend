package com.in28minutes.rest.webservices.restfulwebservices.course;
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

@RestController
//@CrossOrigin
public class CourseResource {

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/course/get/all")
	public ResponseEntity<Object> getAllCourse() {
		// if there's a @PathVariable long courseId in method parameter
		// Then it must be part of URI variable also like {courseId}/course
		// else will have error
//		return commentRepository.findByUsername(username);
		List<Course> listOfAllCourses = courseRepository.findAll();
		if (listOfAllCourses.size() > 0) {
			// Return details of all users
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(listOfAllCourses);

//					new ResponseEntity<>(listOfAllCourses , HttpStatus.OK);

		} else {
			// List of all users are empty
//			return ResponseEntity.noContent().build();
			ApiError apiError = new ApiError(HttpStatus.OK, "No courses");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	
	@GetMapping("/course/get/{courseId}")
	public ResponseEntity<Object> getCourseUsingID(@PathVariable long courseId) {
//		return  courseRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(courseId);


		Course course = findCourse(courseId);
		if(course!=null) {
			
			// Return course
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(course);
			
		}else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Course not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		
	

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		courseRepository.findby
//		courseRepository.find
//		return courseRepository.findAllById(tempList);
	}

//	
	@GetMapping("/course/get/usingInstructorApplicationUserId/{instructorApplicationUserId}")
	public ResponseEntity<Object> getCoursesUsingInstructorApplicationUserId(@PathVariable long instructorApplicationUserId) {
//		return  courseRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(courseId);
		Course a = new Course();
		a.setInstructorApplicationUserId(instructorApplicationUserId);
		
		List<Course> courses = courseRepository.findCoursesByInstructorApplicationUserId(instructorApplicationUserId);
		if(courses.size() >0) {
			
			// Return list of Courses related to an instructor application user ID
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(courses);
			
		}else {
			// No courses related to an instructor application user ID
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No courses associated with instructor application user ID");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		courseRepository.findby
//		courseRepository.find
//		return courseRepository.findAllById(tempList);
	}
	
	
	
//	@DeleteMapping("/course/delete/{email}/{courseId}/{commentId}")
//	public ResponseEntity<Object> deleteCourseByEmailByCourseIdByCommentId(
//			@PathVariable String email,
//			@PathVariable long courseId,
//			@PathVariable long commentId) {
//
////		Comment comment = commentService.deleteById(id);
////		if(comment!=null) {
////			return ResponseEntity.noContent().build();
////		}
//		
//		Course courseCheck = new Course();
//		courseCheck.setUsername(email);
//		courseCheck.setCourseId(courseId);
//		courseCheck.setCommentId(commentId);
//		
//		Course course = findCoursesByUsernameByCourseIdAndCommentId(courseCheck);
//		
//		if (course!= null) {
//			
//			courseRepository.deleteCourseByEmailByCourseIdByCommentId(
//					course.getUsername(), course.getCourseId(), course.getCommentId());
//			
//			//Return and display successfully deleted Course
//			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
//					.body(course);
//		}else {
//			
//			//Notify that no such Course exists
//			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Course to delete");
//			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
//					.body(apiError);
//			
//		}
//		
//		
//
////		return ResponseEntity.noContent().build();
//	}

	
	
	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/course/delete/{courseId}")
	public ResponseEntity<Object> deleteCourse(@PathVariable long courseId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		
		Course course = findCourse(courseId);
		
		if (course!= null) {
			
			courseRepository.deleteById(courseId);
			
			//Return and display successfully deleted Course
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(course);
		}else {
			
			//Notify that no such Course exists
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Course to delete");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
			
		}
		
		

//		return ResponseEntity.noContent().build();
	}

	
	@PutMapping("/course/update")
	public ResponseEntity<Object> updateCourse(
			@RequestBody Course course) {
//		Comment commentUpdated = commentService.save(comment);

//		comment.setUsername(username);

		if (courseRepository.findById(course.getId()).isPresent()) {
			Course courseUpdated = courseRepository.save(course);
//			return new ResponseEntity<Comment>(comment, HttpStatus.OK);

			// Return and display newly updated Course
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(courseUpdated);
		} else {
			
			//Notify of the updating of a nonexistent Course 
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Course to be updated");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	@PostMapping("/course/create")
	public ResponseEntity<Object> createCourse(@RequestBody Course course) {

//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);

//		comment.setId(-1L);

//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);
		
		if (courseRepository.findById(course.getId()).isPresent() ) {
			System.out.println("Course already exists: "+courseRepository.findById(course.getId()));
//			toReturn.add(f);

			// Return response that notifies that Course already exists
			ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Course already exists");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
		} else {
			// Notify of successful addition of new course
			Course createdCourse = courseRepository.save(course);
			// Return and display newly created Course
			return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin")
					.body(createdCourse);
		}

		// Location
		// Get current resouce url
		// {id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/course/get/{id}").buildAndExpand(createdCourse.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();

	}

//	protected Course findCoursesByUsernameByCourseIdAndCommentId(Course course) {
//		List<Course> temp = new ArrayList<Course>();
//		temp = courseRepository.findAll();
//
//	
//		
////		List<Course> toReturn = new ArrayList<Course>();
//		for (Course f : temp) {
//
//			if (f.getUsername().equals(course.getUsername())
//					&& f.getCourseId() == course.getCourseId()
//					&& f.getCommentId() == course.getCommentId()) {
//				return f;
//			}
//		}
//
//		return null;
//
//	}
	
//	protected List<Course> findCoursesByUsernameByCourseId(Course course) {
//		List<Course> temp = new ArrayList<Course>();
////		temp = courseRepository.findAll();
//
//		List<Course> uf = courseRepository.findCoursesByEmailByCourseId(course.getUsername() , course.getCourseId());
//		return uf;
////		List<Course> toReturn = new ArrayList<Course>();
////		for (Course f : temp) {
////
////			if (f.getUsername().equals(course.getUsername())
////					&& f.getCourseId() == course.getCourseId()) {
////				return f;
////			}
////		}
////
////		return null;
//
//	}
	

	protected Course findCourse(long courseId) {
		List<Course> temp = new ArrayList<Course>();
		temp = courseRepository.findAll();

//		List<Course> toReturn = new ArrayList<Course>();
		for (Course f : temp) {

			if (f.getId() == courseId) {
				return f;
			}
		}

		return null;

	}
}







//@GetMapping("/course/get/authenticate/{email}/{password}")
//public ResponseEntity<Object> authenticateCourse(@PathVariable String email,
//		@PathVariable String password) {
////	return  courseRepository.findById(id).get();
//
////	List<Long> tempList = new ArrayList<Long>();
////	tempList.add(courseId);
//	Course a = new Course();
//	a.setEmail(email);
//	Course course = findUser(a);
//	if(course.getPassword().equals(password)) {
//		
//		// Return user detail
//		return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(course);
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
////	courseRepository.findby
////	courseRepository.find
////	return courseRepository.findAllById(tempList);
//}
