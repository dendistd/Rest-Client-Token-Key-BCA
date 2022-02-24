package com.example.client.payload.token;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenModel {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("serial_number")
	private String serialNumber;
	
	@JsonProperty("cabang")
	private String cabang;
	
	@JsonProperty("status")
	private String status;

	public TokenModel() {
		super();
	}
	
	public TokenModel(String id, String serialNumber, String cabang, String status) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.cabang = cabang;
		this.status = status;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCabang() {
		return cabang;
	}

	public void setCabang(String cabang) {
		this.cabang = cabang;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "TokenModel [id=" + id + ", serialNumber=" + serialNumber + ", cabang=" + cabang + ", status=" + status
				+  "]";
	}


}
