package com.example.client.payload.koneksi.create;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputCreateKoneksi {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("date")
	private Date dateKoneksi;
	
	@JsonProperty("serial_number")
	private String serianNumber;
	
	@JsonProperty("jenis_koneksi")
	private String jenisKoneksi;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("cabang")
	private String cabang;

	public OutputCreateKoneksi() {
		super();
	}
	
	public OutputCreateKoneksi(String id, Date dateKoneksi, String serianNumber, String jenisKoneksi, String status,
			String cabang) {
		super();
		this.id = id;
		this.dateKoneksi = dateKoneksi;
		this.serianNumber = serianNumber;
		this.jenisKoneksi = jenisKoneksi;
		this.status = status;
		this.cabang = cabang;
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

	public String getSerianNumber() {
		return serianNumber;
	}

	public void setSerianNumber(String serianNumber) {
		this.serianNumber = serianNumber;
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

	public String getCabang() {
		return cabang;
	}

	public void setCabang(String cabang) {
		this.cabang = cabang;
	}

	@Override
	public String toString() {
		return "Koneksi [id=" + id + ", dateKoneksi=" + dateKoneksi + ", serianNumber=" + serianNumber
				+ ", jenisKoneksi=" + jenisKoneksi + ", status=" + status + ", cabang=" + cabang + "]";
	}
	

}
