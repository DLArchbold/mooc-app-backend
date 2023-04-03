package com.in28minutes.rest.webservices.restfulwebservices.comment;

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
@Table(name="Comment")
@EntityScan
public class Comment {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length=100000)
	private String description;
	
	private String urgencyLevel;
	private long inResponseTo;
	private Date targetDate;
	private String username;
	private long votes;
	private long lessonId;

	protected Comment() {

	}

	public Comment(long id, String description, String urgencyLevel, 
			long inResponseTo, Date targetDate,
			String username, long votes,
			long lessonId) {
		super();
		this.id = id;
		this.description = description;
		this.urgencyLevel = urgencyLevel;
		this.inResponseTo = inResponseTo;
		this.targetDate = targetDate;
		this.username = username;
		this.votes = votes;
		this.lessonId = lessonId;
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

	/**
	 * @return the votes
	 */
	public long getVotes() {
		return votes;
	}

		

	/**
	 * @param votes the votes to set
	 */
	public void setVotes(long votes) {
		this.votes = votes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(long inResponseTo) {
		this.inResponseTo = inResponseTo;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrgencyLevel() {
		return urgencyLevel;
	}

	public void setUrgencyLevel(String urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, inResponseTo, lessonId, targetDate, urgencyLevel, username, votes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(description, other.description) && id == other.id && inResponseTo == other.inResponseTo
				&& lessonId == other.lessonId && Objects.equals(targetDate, other.targetDate)
				&& Objects.equals(urgencyLevel, other.urgencyLevel) && Objects.equals(username, other.username)
				&& votes == other.votes;
	}

}
