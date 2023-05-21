package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class ApplicationUserRest {


	private String email;
	private String userType;
	private Long lessonId;
	private String name;
	private String Interests;
	
	public ApplicationUserRest() {
		
	}
	/**
	 * @param email
	 * @param userType
	 * @param lessonId
	 * @param name
	 * @param interests
	 */
	public ApplicationUserRest(String email, String userType, Long lessonId, String name, String interests) {
		this.email = email;
		this.userType = userType;
		this.lessonId = lessonId;
		this.name = name;
		this.Interests = interests;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the lessonId
	 */
	public Long getLessonId() {
		return lessonId;
	}
	/**
	 * @param lessonId the lessonId to set
	 */
	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the interests
	 */
	public String getInterests() {
		return Interests;
	}
	/**
	 * @param interests the interests to set
	 */
	public void setInterests(String interests) {
		Interests = interests;
	}
	
	
    
}
