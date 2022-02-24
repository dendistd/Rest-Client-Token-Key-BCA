package com.example.client.payload.koneksi.delete;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputDeleteKoneksi {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("date_koneksi")
	private Date dateKoneksi;
	
	@JsonProperty("serial_number")
	private String serialNumber;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("jenis_koneksi")
	private String jenisKoneksi;
	
	@JsonProperty("status")
	private String status;

	public OutputDeleteKoneksi() {
		super();
	}

	public OutputDeleteKoneksi(String id, Date dateKoneksi, String serialNumber, String userId, String jenisKoneksi,
			String status) {
		super();
		this.id = id;
		this.dateKoneksi = dateKoneksi;
		this.serialNumber = serialNumber;
		this.userId = userId;
		this.jenisKoneksi = jenisKoneksi;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateKoneksi() {
		return dateKoneksi;
	}

	public void setDateKoneksi(Date dateKoneksi) {
		this.dateKoneksi = dateKoneksi;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJenisKoneksi() {
		return jenisKoneksi;
	}

	public void setJenisKoneksi(String jenisKoneksi) {
		this.jenisKoneksi = jenisKoneksi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OutputDeleteKoneksi [id=" + id + ", dateKoneksi=" + dateKoneksi + ", serialNumber=" + serialNumber
				+ ", userId=" + userId + ", jenisKoneksi=" + jenisKoneksi + ", status=" + status + "]";
	}
	
}
