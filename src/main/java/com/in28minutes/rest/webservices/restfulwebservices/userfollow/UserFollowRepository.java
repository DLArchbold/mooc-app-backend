package com.in28minutes.rest.webservices.restfulwebservices.userfollow;

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
public interface UserFollowRepository extends JpaRepository<UserFollow, Long>{
	
	String q1= "SELECT c FROM UserFollow c"
			+ " WHERE c.username = :email "
			+ "AND c.lessonId = :lessonId";
	
	//Use names for properties of entities when defining query
	@Query(q1)
	List<UserFollow> findUserFollowsByEmailByLessonId(
			@Param("email") String email,
			@Param("lessonId") long lessonId);
	
	
	
	
	String q2= "DELETE FROM UserFollow "
			+ " WHERE username = :email "
			+ "AND lessonId= :lessonId "
			+ "AND commentId = :commentId " ;
	
	//Use names for properties of entities when defining query
	//Custom DELETE JPQL must have below annotations and return void
	@Modifying
	@Transactional
	@Query(q2)
	void deleteUserFollowByEmailByLessonIdByCommentId(
			@Param("email") String email,
			@Param("lessonId") long lessonId,
			@Param("commentId") long commentId);
	
	
	
	
	
	String q3= "SELECT c FROM Comment c"
			+ " WHERE c.lessonId = :lessonId " 
			+ "AND c.inResponseTo = :inResponseTo";
	
	//Use names for properties of entities when defining query
	@Query(q3)
	List<UserFollow> findTopLevelCommentByLessonId(
			@Param("lessonId") long lessonId,
			@Param("inResponseTo") long inResponseTo);
	
	
	
	
	
	
	String q4= "SELECT c FROM Comment c"
			+ " WHERE c.lessonId = :lessonId "
			+ "AND c.username = :email "
			+ "AND c.inResponseTo = :inResponseTo";
	
	//Use names for properties of entities when defining query
	@Query(q4)
	List<UserFollow> findTopLevelCommentByLessonIdByEmail(
			@Param("lessonId") long lessonId,
			@Param("email") String email,
			@Param("inResponseTo") long inResponseTo);
	
	//service.retrieveTodos(name)

	//service.deleteTodo(id);
	//service.retrieveTodo(id)
	//service.updateTodo(todo)
	//service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),false);
}




