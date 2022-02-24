package com.example.client.payload.koneksi.get;

import java.util.List;

import com.example.client.payload.ErrorSchema;
import com.example.client.payload.koneksi.Koneksi;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGetKoneksiSchema {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	private List<Koneksi> outputSchema;

	public ResponseGetKoneksiSchema() {
		super();
	}

	public ResponseGetKoneksiSchema(ErrorSchema errorSchema, List<Koneksi> outputSchema) {
		super();
		this.errorSchema = errorSchema;
		this.outputSchema = outputSchema;
	}

	public ErrorSchema getErrorSchema() {
		return errorSchema;
	}

	public void setErrorSchema(ErrorSchema errorSchema) {
		this.errorSchema = errorSchema;
	}

	public List<Koneksi> getOutputSchema() {
		return outputSchema;
	}

	public void setOutputSchema(List<Koneksi> outputSchema) {
		this.outputSchema = outputSchema;
	}

	@Override
	public String toString() {
		return "ResponseGetKoneksiSchema [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}
	
	

}
