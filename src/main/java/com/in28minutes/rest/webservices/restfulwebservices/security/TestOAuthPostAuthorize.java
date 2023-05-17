package com.in28minutes.rest.webservices.restfulwebservices.security;

public class TestOAuthPostAuthorize {

	private String userFirstName;
	private String userLastName;	
	private String userId;
	/**
	 * @param userFirstName
	 * @param userLastName
	 * @param userId
	 */
	public TestOAuthPostAuthorize(String userFirstName, String userLastName, String userId) {
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userId = userId;
	}
	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}
	/**
	 * @param userFirstName the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}
	/**
	 * @param userLastName the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
