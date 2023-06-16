package com.in28minutes.rest.webservices.restfulwebservices.userfollow;

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

import com.in28minutes.rest.webservices.restfulwebservices.ApiError;

@RestController
//@CrossOrigin
public class UserFollowResource {

	@Autowired
	private UserFollowRepository userFollowRepository;

	@GetMapping("/user_follow/get/all")
	public ResponseEntity<Object> getAllUserFollow() {
		// if there's a @PathVariable long lessonId in method parameter
		// Then it must be part of URI variable also like {lessonId}/user_follow
		// else will have error
//		return commentRepository.findByUsername(username);
		List<UserFollow> listOfAllUserFollows = userFollowRepository.findAll();
		if (listOfAllUserFollows.size() > 0) {
			// Return details of all users
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(listOfAllUserFollows);

//					new ResponseEntity<>(listOfAllUserFollows , HttpStatus.OK);

		} else {
			// List of all users are empty
//			return ResponseEntity.noContent().build();
			ApiError apiError = new ApiError(HttpStatus.OK, "No users following any comments");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	
	@GetMapping("/user_follow/get/{userFollowId}")
	public ResponseEntity<Object> getUserFollowUsingID(@PathVariable long userFollowId) {
//		return  userFollowRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(userFollowId);


		UserFollow userFollow = findUserFollow(userFollowId);
		if(userFollow!=null) {
			
			// Return user detail
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(userFollow);
			
		}else {
			// User not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		
	

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		userFollowRepository.findby
//		userFollowRepository.find
//		return userFollowRepository.findAllById(tempList);
	}

	
	@GetMapping("/user_follow/get/usingEmailAndLessonId/{email}/{lessonId}")
	public ResponseEntity<Object> getUserFollowsUsingEmailAndLessonId(@PathVariable String email,@PathVariable long lessonId) {
//		return  userFollowRepository.findById(id).get();

//		List<Long> tempList = new ArrayList<Long>();
//		tempList.add(userFollowId);
		UserFollow a = new UserFollow();
		a.setUsername(email);
		a.setLessonId(lessonId);
		List<UserFollow> userFollows = findUserFollowsByUsernameByLessonId(a);
		if(userFollows.size() >0) {
			
			// Return UserFollow detail
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(userFollows);
			
		}else {
			// UserFollow not found
			// Implementation refer to #7
//			https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "UserFollow not found");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin").body(apiError);
		}
		

//		return ResponseEntity.noContent().build();

//		return toReturn;

//		userFollowRepository.findby
//		userFollowRepository.find
//		return userFollowRepository.findAllById(tempList);
	}
	
	
	
	@DeleteMapping("/user_follow/delete/{email}/{lessonId}/{commentId}")
	public ResponseEntity<Object> deleteUserFollowByEmailByLessonIdByCommentId(
			@PathVariable String email,
			@PathVariable long lessonId,
			@PathVariable long commentId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		
		UserFollow userFollowCheck = new UserFollow();
		userFollowCheck.setUsername(email);
		userFollowCheck.setLessonId(lessonId);
		userFollowCheck.setCommentId(commentId);
		
		UserFollow userFollow = findUserFollowsByUsernameByLessonIdAndCommentId(userFollowCheck);
		
		if (userFollow!= null) {
			
			userFollowRepository.deleteUserFollowByEmailByLessonIdByCommentId(
					userFollow.getUsername(), userFollow.getLessonId(), userFollow.getCommentId());
			
			//Return and display successfully deleted UserFollow
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(userFollow);
		}else {
			
			//Notify that no such UserFollow exists
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such UserFollow to delete");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
			
		}
		
		

//		return ResponseEntity.noContent().build();
	}

	
	
	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/user_follow/delete/{userFollowId}")
	public ResponseEntity<Object> deleteUserFollow(@PathVariable long userFollowId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		
		UserFollow userFollow = findUserFollow(userFollowId);
		
		if (userFollow!= null) {
			
			userFollowRepository.deleteById(userFollowId);
			
			//Return and display successfully deleted UserFollow
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(userFollow);
		}else {
			
			//Notify that no such UserFollow exists
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such UserFollow to delete");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
			
		}
		
		

//		return ResponseEntity.noContent().build();
	}

	
	@PutMapping("/user_follow/update")
	public ResponseEntity<Object> updateUserFollow(
			@RequestBody UserFollow userFollow) {
//		Comment commentUpdated = commentService.save(comment);

//		comment.setUsername(username);

		if (findUserFollowsByUsernameByLessonIdAndCommentId(userFollow) != null) {
			UserFollow userFollowUpdated = userFollowRepository.save(userFollow);
//			return new ResponseEntity<Comment>(comment, HttpStatus.OK);

			// Return and display newly updated UserFollow
			return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin")
					.body(userFollowUpdated);
		} else {
			
			//Notify of the updating of a nonexistent UserFollow 
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No such UserFollow to be updated");
			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);

		}

	}

	@PostMapping("/user_follow/create")
	public ResponseEntity<Object> createUserFollow(@RequestBody UserFollow userFollow) {

//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);

//		comment.setId(-1L);

//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);

		if (findUserFollowsByUsernameByLessonIdAndCommentId(userFollow) != null) {
			System.out.println("User already exists");
//			toReturn.add(f);

			// Return response that notifies that UserFollow already exists
			ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "UserFollow already exists");

			return ResponseEntity.status(apiError.getStatus()).header("Access-Control-Allow-Origin")
					.body(apiError);
		} else {
			// Notify of successful addition of new user
			UserFollow createdUserFollow = userFollowRepository.save(userFollow);
			// Return and display newly created UserFollow
			return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin")
					.body(createdUserFollow);
		}

		// Location
		// Get current resouce url
		// {id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/user_follow/get/{id}").buildAndExpand(createdUserFollow.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();

	}

	protected UserFollow findUserFollowsByUsernameByLessonIdAndCommentId(UserFollow userFollow) {
		List<UserFollow> temp = new ArrayList<UserFollow>();
		temp = userFollowRepository.findAll();

	
		
//		List<UserFollow> toReturn = new ArrayList<UserFollow>();
		for (UserFollow f : temp) {

			if (f.getUsername().equals(userFollow.getUsername())
					&& f.getLessonId() == userFollow.getLessonId()
					&& f.getCommentId() == userFollow.getCommentId()) {
				return f;
			}
		}

		return null;

	}
	
	protected List<UserFollow> findUserFollowsByUsernameByLessonId(UserFollow userFollow) {
		List<UserFollow> temp = new ArrayList<UserFollow>();
//		temp = userFollowRepository.findAll();

		List<UserFollow> uf = userFollowRepository.findUserFollowsByEmailByLessonId(userFollow.getUsername() , userFollow.getLessonId());
		return uf;
//		List<UserFollow> toReturn = new ArrayList<UserFollow>();
//		for (UserFollow f : temp) {
//
//			if (f.getUsername().equals(userFollow.getUsername())
//					&& f.getLessonId() == userFollow.getLessonId()) {
//				return f;
//			}
//		}
//
//		return null;

	}
	

	protected UserFollow findUserFollow(long userFollowId) {
		List<UserFollow> temp = new ArrayList<UserFollow>();
		temp = userFollowRepository.findAll();

//		List<UserFollow> toReturn = new ArrayList<UserFollow>();
		for (UserFollow f : temp) {

			if (f.getId() == userFollowId) {
				return f;
			}
		}

		return null;

	}
}







//@GetMapping("/user_follow/get/authenticate/{email}/{password}")
//public ResponseEntity<Object> authenticateUserFollow(@PathVariable String email,
//		@PathVariable String password) {
////	return  userFollowRepository.findById(id).get();
//
////	List<Long> tempList = new ArrayList<Long>();
////	tempList.add(userFollowId);
//	UserFollow a = new UserFollow();
//	a.setEmail(email);
//	UserFollow userFollow = findUser(a);
//	if(userFollow.getPassword().equals(password)) {
//		
//		// Return user detail
//		return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin").body(userFollow);
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
////	userFollowRepository.findby
////	userFollowRepository.find
////	return userFollowRepository.findAllById(tempList);
//}
