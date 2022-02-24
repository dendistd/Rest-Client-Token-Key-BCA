package com.example.client.payload.cabang;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputDataCabang {
	@JsonProperty("kode_cabang")
	private String kodeCabang;
	
	@JsonProperty("nama_cabang")
	private String namaCabang;
	
	@JsonProperty("status")
	private String status;

	public OutputDataCabang() {
		super();
	}

	public OutputDataCabang(String kodeCabang, String namaCabang, String status) {
		super();
		this.kodeCabang = kodeCabang;
		this.namaCabang = namaCabang;
		this.status = status;
	}

	public String getKodeCabang() {
		return kodeCabang;
	}

	public void setKodeCabang(String kodeCabang) {
		this.kodeCabang = kodeCabang;
	}

	public String getNamaCabang() {
		return namaCabang;
	}

	public void setNamaCabang(String namaCabang) {
		this.namaCabang = namaCabang;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OutputsCabangs [kodeCabang=" + kodeCabang + ", namaCabang=" + namaCabang + ", status=" + status + "]";
	}
	
	
}
