package com.in28minutes.rest.webservices.restfulwebservices.feedback;

import java.util.Date;
import java.util.Objects;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

@Entity
@EntityScan
public class Feedback {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length = 100000)
	private String feedbackComment;
	
	private Date feedbackTimestamp;
	
	private long feedbackRating;

	private long lessonId;

	private long instructorApplicationUserId;
	
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

	public Feedback() {

	}

	public Feedback(long id, String feedbackComment, 
			Date feedbackTimestamp,
			long feedbackRating, long lessonId,
			long instructorApplicationUserId) {
		super();
		this.id = id;
		this.feedbackComment = feedbackComment;
		this.feedbackTimestamp = feedbackTimestamp;
		this.feedbackRating = feedbackRating;
		this.lessonId = lessonId;
		this.instructorApplicationUserId = instructorApplicationUserId;
	}

	/**
	 * @return the feedbackTimestamp
	 */
	public Date getFeedbackTimestamp() {
		return feedbackTimestamp;
	}

	/**
	 * @param feedbackTimestamp the feedbackTimestamp to set
	 */
	public void setFeedbackTimestamp(Date feedbackTimestamp) {
		this.feedbackTimestamp = feedbackTimestamp;
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
	 * @return the feedbackComment
	 */
	public String getFeedbackComment() {
		return feedbackComment;
	}

	/**
	 * @param feedbackComment the feedbackComment to set
	 */
	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}

	/**
	 * @return the feedbackRating
	 */
	public long getFeedbackRating() {
		return feedbackRating;
	}

	/**
	 * @param feedbackRating the feedbackRating to set
	 */
	public void setFeedbackRating(long feedbackRating) {
		this.feedbackRating = feedbackRating;
	}

	/**
	 * @return the lessonId
	 */
	public long getLessonId() {
		return lessonId;
	}

	/**
	 * @param lessonId the lessonId to set
	 */
	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(feedbackComment, feedbackRating, feedbackTimestamp, id, instructorApplicationUserId,
				lessonId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		return Objects.equals(feedbackComment, other.feedbackComment) && feedbackRating == other.feedbackRating
				&& Objects.equals(feedbackTimestamp, other.feedbackTimestamp) && id == other.id
				&& instructorApplicationUserId == other.instructorApplicationUserId && lessonId == other.lessonId;
	}

}
