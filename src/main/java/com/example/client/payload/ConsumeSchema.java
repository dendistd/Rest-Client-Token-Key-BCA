package com.example.client.payload;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsumeSchema {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	private Map<String, String> outputSchema;

	public ConsumeSchema() {
		super();
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
		return "ConsumeSchema [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}
	
	
}
