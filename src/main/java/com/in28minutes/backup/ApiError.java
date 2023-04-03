package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;
//	private List<String> errors;
	private int code;
	public ApiError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.code = status.value();
		this.message = message;
//		this.errors = errors;
	}

/**
	 * @return the error
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param error the error to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	//	public ApiError(HttpStatus status, String message, String error) {
//		super();
//		this.status = status;
//		this.message = message;
//		errors = Arrays.asList(error);
//	}
	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

//	/**
//	 * @return the errors
//	 */
//	public List<String> getErrors() {
//		return errors;
//	}
//
//	/**
//	 * @param errors the errors to set
//	 */
//	public void setErrors(List<String> errors) {
//		this.errors = errors;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(errors, message, status);
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiError other = (ApiError) obj;
		return Objects.equals(message, other.message) && status == other.status;
	}

	
}
