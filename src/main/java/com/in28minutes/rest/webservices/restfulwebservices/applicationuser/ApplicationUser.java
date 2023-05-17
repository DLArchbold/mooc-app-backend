package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import java.util.Objects;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Lob;

import jakarta.persistence.*;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan
public class ApplicationUser {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length = 100000)
	private String email;
	private String password;
	private String userType;
	private Long lessonId;
	private String name;
	private String Interests;

	public ApplicationUser() {

	}

	/**
	 * @param id
	 * @param email
	 * @param name
	 * @param password
	 * @param userType
	 * @param lessonId
	 * @param Interests
	 */
	public ApplicationUser(long id, String name, String email, String password, String userType, Long lessonId, String Interests) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.userType = userType;
		this.lessonId = lessonId;
		this.Interests = Interests;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the id to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the LessonId
	 */
	public Long getLessonId() {
		return lessonId;
	}

	/**
	 * @param lessonId the LessonId to set
	 */
	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	/**
	 * @return the Interests
	 */
	public String getInterests() {
		return Interests;
	}

	/**
	 * @param Interests the userType to set
	 */
	public void setInterests(String Interests) {
		this.Interests = Interests;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, name, id, password, userType, Interests);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationUser other = (ApplicationUser) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)&& Objects.equals(name, other.name)
				&& Objects.equals(userType, other.userType) && Objects.equals(lessonId, other.lessonId)&& Objects.equals(Interests, other.Interests);
	}

}
