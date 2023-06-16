package com.in28minutes.rest.webservices.restfulwebservices.comment;

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




@RestController
//@CrossOrigin
public class CommentResource     {

	@Autowired 
	private CommentRepository commentRepository;
	
	@GetMapping("/users/comments")
	public List<Comment> getAllComments(){
//		return commentRepository.findByUsername(username);
		return commentRepository.findAll();
	}
	
	@GetMapping("/users/{username}/comments/{id}")
	public Comment getCommentUsingId(@PathVariable String username, @PathVariable long id){
		return  commentRepository.findById(id).get();
	}
	
//	@GetMapping("/users/{email}/{inResponseTo}/comments")
//	public List<Comment> getTopLevelCommentsForLessonByEmail(
//			@PathVariable String email,
//			@PathVariable long inResponseTo){
//		
//		return  commentRepository.findByEmail(email, inResponseTo);
//	}
	
	
	@GetMapping("/users/comments/{lessonId}/all")
	public List<Comment> getCommentsByLessonId(
			@PathVariable long lessonId){
		
		return  commentRepository.findCommentsByLessonId(lessonId);
	}
	
	@GetMapping("/users/comments/{lessonId}/all/toplevel")
	public List<Comment> getTopLevelCommentByLessonId(
			@PathVariable long lessonId){
		
		return  commentRepository.findTopLevelCommentByLessonId(lessonId, 0);
	}
	
	@GetMapping("/users/comments/{lessonId}/{email}/all/toplevel")
	public List<Comment> getTopLevelCommentByLessonIdByEmail(
			@PathVariable long lessonId,
			@PathVariable String email){
		
		return  commentRepository.findTopLevelCommentByLessonIdByEmail(lessonId, email, 0);
	}
	
	@GetMapping("/users/comments/{lessonId}/{email}/all/toplevel/ToRespond")
	public List<Long> getTopLevelCommentIdsByLessonIdByEmailToRespond(
			@PathVariable long lessonId,
			@PathVariable String email){
		
		List<Long> ids = new ArrayList<>();
		List<Comment> comments =commentRepository.findTopLevelCommentByLessonIdByEmailExcludeUser(lessonId, email, 0);
		for(int i = 0; i<comments.size(); i++) {
			long id = comments.get(i).getId();
			ids.add(id);
		}
		
		
		return  ids;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/users/{username}/comments/{id}")
	public ResponseEntity<Void> deleteComment(
			@PathVariable String username, @PathVariable long id){
		
//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		commentRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/users/{username}/comments/{id}")
	public ResponseEntity<Comment> updateComment(
			@PathVariable String username, 
			@PathVariable long id, @RequestBody Comment comment){
//		Comment commentUpdated = commentService.save(comment);
		
		
		comment.setUsername(username);
		
		Comment commentUpdated = commentRepository.save(comment);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/users/{username}/comments")
	public ResponseEntity<Object> createComment(
			@PathVariable String username, @RequestBody Comment comment){
		
//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);
		
		
//		comment.setId(-1L);
		
		
//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);
		
//		return commentRepository.insertComment(comment.getDescription(), comment.getUrgencyLevel(), comment.getInResponseTo(),
//				comment.getTargetDate(), comment.getUsername(), comment.getVotes(), comment.getLessonId());
		
		
		Comment createdComment= commentRepository.save(comment);
		
		
		//Location
		//Get current resouce url
		//{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(createdComment.getId()).toUri();
		
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
}


//@RestController
////@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin
//public class CommentResource {
//
//	@Autowired 
//	private CommentHardcodedService commentService;
//	
//	@GetMapping("/users/{username}/comments")
//	public List<Comment> getAllComments(@PathVariable String username){
//		return commentService.findAll();
//	}
//	
//	@GetMapping("/users/{username}/comments/{id}")
//	public Comment getComment(@PathVariable String username, @PathVariable long id){
//		return commentService.findById(id);
//	}
//	
//	
//	//DELETE /users/{username}/todos/{id}
//	@DeleteMapping("/users/{username}/comments/{id}")
//	public ResponseEntity<Void> deleteComment(
//			@PathVariable String username, @PathVariable long id){
//		
//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
//		
//		return ResponseEntity.notFound().build();
//	}
//	
//	
//	@PutMapping("/users/{username}/comments/{id}")
//	public ResponseEntity<Comment> updateComment(
//			@PathVariable String username, 
//			@PathVariable long id, @RequestBody Comment comment){
//		Comment commentUpdated = commentService.save(comment);
//		
//		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
//		
//	}
//	
//	
//	@PostMapping("/users/{username}/comments")
//	public ResponseEntity<Comment> createComment(
//			@PathVariable String username, @RequestBody Comment comment){
//		
//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);
//		
//		//Location
//		//Get current resouce url
//		//{id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//			.path("/{id}").buildAndExpand(createdComment.getId()).toUri();
//		
//		
//		return ResponseEntity.created(uri).build();
//		
//	}
//	
//	
//}
