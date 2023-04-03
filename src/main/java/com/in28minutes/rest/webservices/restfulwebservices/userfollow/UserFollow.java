package com.in28minutes.rest.webservices.restfulwebservices.userfollow;

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
@Table(name="UserFollow")
@EntityScan
public class UserFollow {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GenericGenerator(name = "startfromlargestid", strategy = "increment")
	@GeneratedValue(generator = "startfromlargestid")
	private long id;

	@Lob
	@Column(length=100000)
	
	private String username;
	private long lessonId;
	private long commentId;

	protected UserFollow() {

	}

	/**
	 * @param id
	 * @param username
	 * @param lessonId
	 * @param commentId
	 */
	public UserFollow(long id, String username, long lessonId, long commentId) {
		super();
		this.id = id;
		this.username = username;
		this.lessonId = lessonId;
		this.commentId = commentId;
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
	 * @return the commentId
	 */
	public long getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}



	@Override
	public int hashCode() {
		return Objects.hash(commentId, id, lessonId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFollow other = (UserFollow) obj;
		return commentId == other.commentId && id == other.id && lessonId == other.lessonId
				&& Objects.equals(username, other.username);
	}

	

}
