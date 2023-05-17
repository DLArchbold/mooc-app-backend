package com.in28minutes.rest.webservices.restfulwebservices.lesson;

import java.util.Date;
import java.util.Objects;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

@Entity
@Table(name="Lesson")
@EntityScan
public class Lesson {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length=100000)
	
	private String videoLink;
	private long lessonNumber;
	private long courseId;
	private String description;

	protected Lesson() {

	}

	/**
	 * @param id
	 * @param videoLink
	 * @param lessonNumber
	 * @param courseId
	 * @param description
	 */
	public Lesson(long id, String videoLink, long lessonNumber, long courseId, String description) {
		super();
		this.id = id;
		this.videoLink = videoLink;
		this.lessonNumber = lessonNumber;
		this.courseId = courseId;
		this.description = description;
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
	 * @return the videoLink
	 */
	public String getVideoLink() {
		return videoLink;
	}

	/**
	 * @param videoLink the videoLink to set
	 */
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	/**
	 * @return the lessonNumber
	 */
	public long getLessonNumber() {
		return lessonNumber;
	}

	/**
	 * @param lessonNumber the lessonNumber to set
	 */
	public void setLessonNumber(long lessonNumber) {
		this.lessonNumber = lessonNumber;
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

	@Override
	public int hashCode() {
		return Objects.hash(courseId, description, id, lessonNumber, videoLink);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return courseId == other.courseId && Objects.equals(description, other.description) && id == other.id
				&& lessonNumber == other.lessonNumber && Objects.equals(videoLink, other.videoLink);
	}


	

}
