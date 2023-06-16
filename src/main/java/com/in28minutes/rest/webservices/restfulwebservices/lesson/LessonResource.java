package com.in28minutes.rest.webservices.restfulwebservices.lesson;
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
public class LessonResource {

	@Autowired
	private LessonRepository lessonRepository;

	@GetMapping("/lesson/get/all")
	public ResponseEntity<Object> getAllLesson() {
		// if there's a @PathVariable long lessonId in method parameter
		// Then it must be part of URI variable also like {lessonId}/lesson
		// else will have error
//		return commentRepository.findByUsername(username);
		List<Lesson> listOfAllLessons = lessonRepository.findAll();
		if (listOfAllLessons.size() > 0) {
			// Return details of all users
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(listOfAllLessons);

//					new ResponseEntity<>(listOfAllLessons , HttpStatus.OK);

		} else {
			// List of all users are empty
//			return ResponseEntity.noContent().build();
			ApiError apiError = new ApiError(HttpStatus.OK, "No lessons");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	
	@GetMapping("/lesson/get/{lessonId}")
	public ResponseEntity<Object> getLessonUsingID(@PathVariable long lessonId) {
//		return  lessonRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(lessonId);


		Lesson lesson = findLesson(lessonId);
		if(lesson!=null) {
			
			// Return lesson
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(lesson);
			
		}else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Lesson not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		
	

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		lessonRepository.findby
//		lessonRepository.find
//		return lessonRepository.findAllById(tempList);
	}

	
	@GetMapping("/lesson/get/usingCourseId/{courseId}")
	public ResponseEntity<Object> getLessonsUsingCourseId(@PathVariable long courseId) {
//		return  lessonRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(lessonId);
		Lesson a = new Lesson();
		a.setCourseId(courseId);
		
		List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);
		if(lessons.size() >0) {
			
			// Return list of Lessons related to a course
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(lessons);
			
		}else {
			// No lessons related to a course
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No lessons associated with course");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		lessonRepository.findby
//		lessonRepository.find
//		return lessonRepository.findAllById(tempList);
	}
	
	
	
//	@DeleteMapping("/lesson/delete/{email}/{lessonId}/{commentId}")
//	public ResponseEntity<Object> deleteLessonByEmailByLessonIdByCommentId(
//			@PathVariable String email,
//			@PathVariable long lessonId,
//			@PathVariable long commentId) {
//
////		Comment comment = commentService.deleteById(id);
////		if(comment!=null) {
////			return ResponseEntity.noContent().build();
////		}
//		
//		Lesson lessonCheck = new Lesson();
//		lessonCheck.setUsername(email);
//		lessonCheck.setLessonId(lessonId);
//		lessonCheck.setCommentId(commentId);
//		
//		Lesson lesson = findLessonsByUsernameByLessonIdAndCommentId(lessonCheck);
//		
//		if (lesson!= null) {
//			
//			lessonRepository.deleteLessonByEmailByLessonIdByCommentId(
//					lesson.getUsername(), lesson.getLessonId(), lesson.getCommentId());
//			
//			//Return and display successfully deleted Lesson
//			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
//					.body(lesson);
//		}else {
//			
//			//Notify that no such Lesson exists
//			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Lesson to delete");
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
	@DeleteMapping("/lesson/delete/{lessonId}")
	public ResponseEntity<Object> deleteLesson(@PathVariable long lessonId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		
		Lesson lesson = findLesson(lessonId);
		
		if (lesson!= null) {
			
			lessonRepository.deleteById(lessonId);
			
			//Return and display successfully deleted Lesson
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(lesson);
		}else {
			
			//Notify that no such Lesson exists
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Lesson to delete");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
			
		}
		
		

//		return ResponseEntity.noContent().build();
	}

	
	@PutMapping("/lesson/update")
	public ResponseEntity<Object> updateLesson(
			@RequestBody Lesson lesson) {
//		Comment commentUpdated = commentService.save(comment);

//		comment.setUsername(username);

		if (lessonRepository.findById(lesson.getId()) != null) {
			Lesson lessonUpdated = lessonRepository.save(lesson);
//			return new ResponseEntity<Comment>(comment, HttpStatus.OK);

			// Return and display newly updated Lesson
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(lessonUpdated);
		} else {
			
			//Notify of the updating of a nonexistent Lesson 
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such Lesson to be updated");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	@PostMapping("/lesson/create")
	public ResponseEntity<Object> createLesson(@RequestBody Lesson lesson) {

//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);

//		comment.setId(-1L);

//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);
		
		
		
		
		
		if (lessonRepository.findById(lesson.getId()).isPresent() ) {
			System.out.println("Lesson already exists: "+lessonRepository.findById(lesson.getId()));
//			toReturn.add(f);

			// Return response that notifies that Lesson already exists
			ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Lesson already exists");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
		} else {
			// Notify of successful addition of new lesson
			Lesson createdLesson = lessonRepository.save(lesson);
			// Return and display newly created Lesson
			return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin")
					.body(createdLesson);
		}

		// Location
		// Get current resouce url
		// {id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/lesson/get/{id}").buildAndExpand(createdLesson.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();

	}

//	protected Lesson findLessonsByUsernameByLessonIdAndCommentId(Lesson lesson) {
//		List<Lesson> temp = new ArrayList<Lesson>();
//		temp = lessonRepository.findAll();
//
//	
//		
////		List<Lesson> toReturn = new ArrayList<Lesson>();
//		for (Lesson f : temp) {
//
//			if (f.getUsername().equals(lesson.getUsername())
//					&& f.getLessonId() == lesson.getLessonId()
//					&& f.getCommentId() == lesson.getCommentId()) {
//				return f;
//			}
//		}
//
//		return null;
//
//	}
	
//	protected List<Lesson> findLessonsByUsernameByLessonId(Lesson lesson) {
//		List<Lesson> temp = new ArrayList<Lesson>();
////		temp = lessonRepository.findAll();
//
//		List<Lesson> uf = lessonRepository.findLessonsByEmailByLessonId(lesson.getUsername() , lesson.getLessonId());
//		return uf;
////		List<Lesson> toReturn = new ArrayList<Lesson>();
////		for (Lesson f : temp) {
////
////			if (f.getUsername().equals(lesson.getUsername())
////					&& f.getLessonId() == lesson.getLessonId()) {
////				return f;
////			}
////		}
////
////		return null;
//
//	}
	

	protected Lesson findLesson(long lessonId) {
		List<Lesson> temp = new ArrayList<Lesson>();
		temp = lessonRepository.findAll();

//		List<Lesson> toReturn = new ArrayList<Lesson>();
		for (Lesson f : temp) {

			if (f.getId() == lessonId) {
				return f;
			}
		}

		return null;

	}
}







//@GetMapping("/lesson/get/authenticate/{email}/{password}")
//public ResponseEntity<Object> authenticateLesson(@PathVariable String email,
//		@PathVariable String password) {
////	return  lessonRepository.findById(id).get();
//
////	List<Long> tempList = new ArrayList<Long>();
////	tempList.add(lessonId);
//	Lesson a = new Lesson();
//	a.setEmail(email);
//	Lesson lesson = findUser(a);
//	if(lesson.getPassword().equals(password)) {
//		
//		// Return user detail
//		return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(lesson);
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
////	lessonRepository.findby
////	lessonRepository.find
////	return lessonRepository.findAllById(tempList);
//}
