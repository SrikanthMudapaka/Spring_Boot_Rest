package com.example.mini.rest.exception;

import java.util.Date;

public class ExceptionResponse {
	// CREATING A STANDARD EXCEPTION TEMPLATE(format) FOR ENTIRE ORGANIZATION
	
	private Date timestamp;
	private String messge;
	private String details;
	
	public ExceptionResponse(Date timestamp, String messge, String details) {
		super();
		this.timestamp = timestamp;
		this.messge = messge;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessge() {
		return messge;
	}

	public void setMessge(String messge) {
		this.messge = messge;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
