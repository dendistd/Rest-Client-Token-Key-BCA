package com.example.client.payload.koneksi.create;

import com.example.client.payload.ErrorSchema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCreateKoneksi {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	private OutputCreateKoneksi outputSchema;

	public ResponseCreateKoneksi() {
		super();
	}
	
	public ResponseCreateKoneksi(ErrorSchema errorSchema) {
		super();
		this.errorSchema = errorSchema;
	}

	public ResponseCreateKoneksi(ErrorSchema errorSchema, OutputCreateKoneksi outputSchema) {
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

	public OutputCreateKoneksi getOutputSchema() {
		return outputSchema;
	}

	public void setOutputSchema(OutputCreateKoneksi outputSchema) {
		this.outputSchema = outputSchema;
	}

	@Override
	public String toString() {
		return "ResponseCreateKoneksi [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}
	
	
	
}
