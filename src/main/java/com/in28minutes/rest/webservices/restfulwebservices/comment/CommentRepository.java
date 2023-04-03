package com.in28minutes.rest.webservices.restfulwebservices.comment;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
//Allows us to perform operations (add, delete, update) on Comment entity
//baeldung.com/spring-data-jpa-query
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	String q1= "SELECT c FROM Comment c"
			+ " WHERE c.username = :email "
			+ "AND c.inResponseTo = :inResponseTo";
	
	//Use names for properties of entities when defining query
	@Query(q1)
	List<Comment> findByEmail(
			@Param("email") String email,
			@Param("inResponseTo") long inResponseTo);
	
	
	
	
	String q2= "SELECT c FROM Comment c"
			+ " WHERE c.lessonId = :lessonId" ;
	
	//Use names for properties of entities when defining query
	@Query(q2)
	List<Comment> findCommentsByLessonId(
			@Param("lessonId") long lessonId);
	
	
	
	
	
	String q3= "SELECT c FROM Comment c"
			+ " WHERE c.lessonId = :lessonId " 
			+ "AND c.inResponseTo = :inResponseTo";
	
	//Use names for properties of entities when defining query
	@Query(q3)
	List<Comment> findTopLevelCommentByLessonId(
			@Param("lessonId") long lessonId,
			@Param("inResponseTo") long inResponseTo);
	
	
	
	
	
	
	String q4= "SELECT c FROM Comment c"
			+ " WHERE c.lessonId = :lessonId "
			+ "AND c.username = :email "
			+ "AND c.inResponseTo = :inResponseTo";
	
	//Use names for properties of entities when defining query
	@Query(q4)
	List<Comment> findTopLevelCommentByLessonIdByEmail(
			@Param("lessonId") long lessonId,
			@Param("email") String email,
			@Param("inResponseTo") long inResponseTo);
	
	
	
	String q5= "SELECT c FROM Comment c"
			+ " WHERE c.lessonId = :lessonId "
			+ "AND c.username != :email "
			+ "AND c.inResponseTo = :inResponseTo";
	
	//Use names for properties of entities when defining query
	@Query(q5)
	List<Comment> findTopLevelCommentByLessonIdByEmailExcludeUser(
			@Param("lessonId") long lessonId,
			@Param("email") String email,
			@Param("inResponseTo") long inResponseTo);
	
	
	
//	String q6= "INSERT into Comment (description, urgency_level, in_response_to, target_date, username, votes, lesson_id) "
//			+ "VALUES (:description, :urgency_level, :in_response_to, :target_date, :username, :votes, :lesson_id)";
//		
//	
//	//Use names for properties of entities when defining query
//	@Transactional
//	@Modifying
//	@Query(q6)
//	int insertComment(
//			@Param("description") String description,
//			@Param("urgency_level") String urgencyLevel,
//			@Param("in_response_to") long in_response_to,
//			@Param("target_date") Date target_date,
//			@Param("username") String username,
//			@Param("votes") long votes,
//			@Param("lesson_id") long lessonId
//			);
//	
//	@Transactional
//	public void insertWithQuery(Person person) {
//	    entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
//	      .setParameter(1, person.getId())
//	      .setParameter(2, person.getFirstName())
//	      .setParameter(3, person.getLastName())
//	      .executeUpdate();
//	}
	
	//service.retrieveTodos(name)

	//service.deleteTodo(id);
	//service.retrieveTodo(id)
	//service.updateTodo(todo)
	//service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),false);
}




