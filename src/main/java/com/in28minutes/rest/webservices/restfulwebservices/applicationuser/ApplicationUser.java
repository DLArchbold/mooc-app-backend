package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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

	public ApplicationUser() {

	}

	/**
	 * @param id
	 * @param email
	 * @param password
	 * @param userType
	 */
	public ApplicationUser(long id, String email, String password, String userType) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.userType = userType;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, userType);
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
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(userType, other.userType);
	}

}
