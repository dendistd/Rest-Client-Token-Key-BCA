package com.example.client.payload.cabang.get;

import java.util.List;

import com.example.client.payload.ErrorSchema;
import com.example.client.payload.cabang.OutputDataCabang;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsesCabangs {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	private List<OutputDataCabang> outputSchema;

	public ResponsesCabangs() {
		super();
	}
	
	public ResponsesCabangs(ErrorSchema errorSchema) {
		super();
		this.errorSchema = errorSchema;
	}

	public ResponsesCabangs(ErrorSchema errorSchema, List<OutputDataCabang> outputSchema) {
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

	public List<OutputDataCabang> getOutputSchema() {
		return outputSchema;
	}

	public void setOutputSchema(List<OutputDataCabang> outputSchema) {
		this.outputSchema = outputSchema;
	}

	@Override
	public String toString() {
		return "ResponsesCabangs [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}

	
}
