package com.example.client.payload.token.get;

import java.util.List;

import com.example.client.payload.ErrorSchema;
import com.example.client.payload.token.TokenModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGetToken {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	private List<TokenModel> outputSchema;

	public ResponseGetToken() {
		super();
	}

	public ResponseGetToken(ErrorSchema errorSchema) {
		super();
		this.errorSchema = errorSchema;
	}

	public ResponseGetToken(ErrorSchema errorSchema, List<TokenModel> outputSchema) {
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

	public List<TokenModel> getOutputSchema() {
		return outputSchema;
	}

	public void setOutputSchema(List<TokenModel> outputSchema) {
		this.outputSchema = outputSchema;
	}

	@Override
	public String toString() {
		return "ResponseGetToken [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}
	
	


}
