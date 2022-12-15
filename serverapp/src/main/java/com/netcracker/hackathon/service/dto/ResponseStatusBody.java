package com.netcracker.hackathon.service.dto;

import org.springframework.http.HttpStatus;

public class ResponseStatusBody {
	
	private String message;
	
	private HttpStatus status;
	
	private Integer code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
