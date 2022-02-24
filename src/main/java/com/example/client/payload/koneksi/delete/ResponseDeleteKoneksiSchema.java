package com.example.client.payload.koneksi.delete;

import java.util.Map;

import com.example.client.payload.ErrorSchema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDeleteKoneksiSchema {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	private Map<String, String> outputSchema;

	public ResponseDeleteKoneksiSchema() {
		super();
	}

	public ResponseDeleteKoneksiSchema(ErrorSchema errorSchema) {
		super();
		this.errorSchema = errorSchema;
	}

	public ErrorSchema getErrorSchema() {
		return errorSchema;
	}

	public void setErrorSchema(ErrorSchema errorSchema) {
		this.errorSchema = errorSchema;
	}

	public Map<String, String> getOutputSchema() {
		return outputSchema;
	}

	public void setOutputSchema(Map<String, String> outputSchema) {
		this.outputSchema = outputSchema;
	}

	@Override
	public String toString() {
		return "ResponseDeleteKoneksiSchema [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}

	
		
}
