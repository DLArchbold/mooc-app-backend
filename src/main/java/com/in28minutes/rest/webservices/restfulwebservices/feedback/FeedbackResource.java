package com.in28minutes.rest.webservices.restfulwebservices.feedback;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.course.Course;
import com.in28minutes.rest.webservices.restfulwebservices.course.CourseRepository;
import com.in28minutes.rest.webservices.restfulwebservices.lesson.Lesson;
import com.in28minutes.rest.webservices.restfulwebservices.lesson.LessonRepository;

@RestController
@CrossOrigin
public class FeedbackResource {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	@GetMapping("/feedback")
	public List<Feedback> getAllFeedback() {
		// if there's a @PathVariable long lessonId in method parameter
		// Then it must be part of URI variable also like {lessonId}/feedback
		// else will have error
//		return commentRepository.findByUsername(username);
		return feedbackRepository.findAll();
	}

	@GetMapping("/{lessonId}/feedback")
	public List<Feedback> getFeebackForALesson(@PathVariable long lessonId) {
//		return  feedbackRepository.findById(id).get();

		List<Long> tempList = new ArrayList<Long>();
		tempList.add(lessonId);
		List<Feedback> temp = new ArrayList<Feedback>();
		temp = feedbackRepository.findAll();
		List<Feedback> toReturn = new ArrayList<Feedback>();
		for (Feedback f : temp) {
			if (f.getLessonId() == lessonId) {
				toReturn.add(f);
			}
		}
		return toReturn;
//		feedbackRepository.findby
//		feedbackRepository.find
//		return feedbackRepository.findAllById(tempList);
	}
	
	@GetMapping("/feedback/getFeedbackForEachLessonInCourse/{courseId}")
	public ArrayList<ArrayList<Feedback>> getFeedbackForEachLessonInCourse(@PathVariable long courseId){
		List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);
		ArrayList<ArrayList<Feedback>> feedbackForEachLessonInCourse = new ArrayList<ArrayList<Feedback>>();
		for(int i =0; i<lessons.size(); i++) {
			feedbackForEachLessonInCourse.add((ArrayList<Feedback>) getFeebackForALesson(lessons.get(i).getId()));
			
		}
		
		return feedbackForEachLessonInCourse;
	}
	
//	@GetMapping("/feedback/getFeedbackForEachLessonInCourse/{courseId}/{startDate}/{endDate}")
//	public ArrayList<ArrayList<Feedback>> getFeedbackForEachLessonInCourseByDate(@PathVariable long courseId,
//			@PathVariable String startDate,
//			@PathVariable String endDate){
//		System.out.println("getFeedbackForEachLessonInCourseByDate");
//		System.out.println("endDate: " + endDate);
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//
////		String dateInString = startDate;
////		if()
//		Date dateStart = null;
//		Date dateEnd = null;
//		if(startDate != "undefined") {
//			  try {
//				  startDate = startDate.substring(0, 10);
//				dateStart= formatter.parse(startDate);
//				System.out.println("startDate: " + startDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		if(!endDate.equals("undefined")) {
//			try {
//				dateEnd= formatter.parse(endDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);
//		ArrayList<ArrayList<Feedback>> feedbackForEachLessonInCourse = new ArrayList<ArrayList<Feedback>>();
//		for(int i =0; i<lessons.size(); i++) {
//			
//			ArrayList<Feedback> a = (ArrayList<Feedback>) getFeebackForALesson(lessons.get(i).getId());
//			for (Feedback f: a) {
//				
//				//Check if Feedback falls between dates
//				if(startDate!="undefined" && endDate !="undefined") {
//					System.out.println("F:" + f.getFeedbackTimestamp().toString());
//					if (f.getFeedbackTimestamp().compareTo(dateStart)>0  && f.getFeedbackTimestamp().compareTo(dateEnd)<0) {
//						feedbackForEachLessonInCourse.add(a);
//					}
//				}else if (startDate!="undefined" && endDate == "undefined") {
//					if (f.getFeedbackTimestamp().compareTo(dateStart)>0) {
//						feedbackForEachLessonInCourse.add(a);
//					}
//				}else if(startDate == "undefined"&& endDate !="undefined") {
//					if(f.getFeedbackTimestamp().compareTo(dateEnd)<0) {
//						feedbackForEachLessonInCourse.add(a);
//					}
//				}
//				
//			}
//			
//			
//		}
//		
//		return feedbackForEachLessonInCourse;
//	}
	
	
	
	
	
	
	
	

	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/{lessonId}/feedback/{feedbackId}")
	public ResponseEntity<Void> deleteFeedback(@PathVariable long lessonId, @PathVariable long feedbackId) {

//		Comment comment = commentService.deleteById(id);
//		if(comment!=null) {
//			return ResponseEntity.noContent().build();
//		}
		feedbackRepository.deleteById(feedbackId);

		return ResponseEntity.noContent().build();
	}

//	
//	@PutMapping("/users/{username}/comments/{id}")
//	public ResponseEntity<Comment> updateComment(
//			@PathVariable String username, 
//			@PathVariable long id, @RequestBody Comment comment){
////		Comment commentUpdated = commentService.save(comment);
//		
//		
//		comment.setUsername(username);
//		
//		Comment commentUpdated = commentRepository.save(comment);
//		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
//		
//	}

	@PostMapping("/{lessonId}/feedback")
	public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {

//		comment.setUsername(username);
//		Comment createdComment = commentService.save(comment);

//		comment.setId(-1L);

//		String a =  comment.getDescription().replaceAll("/'/g", "''");
//		System.out.println("replace: " + a);

		// get courseId related to lessonid (lessonRepository)
		// get instructorId related to courseId (courseRepository)
		// add that to new feedback to be inserted

		Lesson lesson = lessonRepository.findLessonById(feedback.getLessonId());
		Course course = courseRepository.findCourseById(lesson.getCourseId());
		feedback.setInstructorApplicationUserId(course.getInstructorApplicationUserId());
		
		
		Feedback createdFeedback = feedbackRepository.save(feedback);

		// Location
		// Get current resouce url
		// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdFeedback.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

}
