package com.in28minutes.rest.webservices.restfulwebservices.enrolled;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name="Enrolled")
@EntityScan
public class Enrolled {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length=100000)
	
	private String username;
	private long courseId;
	private Date enrolledTimestamp;
	
	/**
	 * @return the enrolledTimestamp
	 */
	public Date getEnrolledTimestamp() {
		return enrolledTimestamp;
	}

	/**
	 * @param enrolledTimestamp the enrolledTimestamp to set
	 */
	public void setEnrolledTimestamp(Date enrolledTimestamp) {
		this.enrolledTimestamp = enrolledTimestamp;
	}

	/**	
	 * @param id
	 * @param username
	 * @param courseId
	 */
	public Enrolled(long id, String username, long courseId, Date enrolledTimestamp) {
		super();
		this.id = id;
		this.username = username;
		this.courseId = courseId;
		this.enrolledTimestamp = enrolledTimestamp;
	}

	public Enrolled() {
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(courseId, enrolledTimestamp, id, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrolled other = (Enrolled) obj;
		return courseId == other.courseId && Objects.equals(enrolledTimestamp, other.enrolledTimestamp)
				&& id == other.id && Objects.equals(username, other.username);
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the courseId
	 */
	public long getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

}