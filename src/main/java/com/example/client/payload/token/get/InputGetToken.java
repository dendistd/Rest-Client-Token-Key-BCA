package com.example.client.payload.token.get;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputGetToken {
	@JsonProperty("status")
	private String status;

	public InputGetToken() {
		super();
	}

	public InputGetToken(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InputGetToken [status=" + status + "]";
	}
	
	

}
