package com.in28minutes.rest.webservices.restfulwebservices.course;

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
@Table(name = "Course")
@EntityScan
public class Course {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length = 100000)
	private String description;
	@Lob
	@Column(length = 100000)
	private String title;
	private long instructorApplicationUserId;

	@Override
	public int hashCode() {
		return Objects.hash(description, id, instructorApplicationUserId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& instructorApplicationUserId == other.instructorApplicationUserId
				&& Objects.equals(title, other.title);
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the instructorApplicationUserId
	 */
	public long getInstructorApplicationUserId() {
		return instructorApplicationUserId;
	}

	/**
	 * @param instructorApplicationUserId the instructorApplicationUserId to set
	 */
	public void setInstructorApplicationUserId(long instructorApplicationUserId) {
		this.instructorApplicationUserId = instructorApplicationUserId;
	}

	
	public Course() {
	}
	/**
	 * @param id
	 * @param description
	 * @param instructorApplicationUserId
	 */
	public Course(long id, String description, long instructorApplicationUserId, String title) {
		super();
		this.id = id;
		this.description = description;
		this.instructorApplicationUserId = instructorApplicationUserId;
		this.title = title;
	}
}
