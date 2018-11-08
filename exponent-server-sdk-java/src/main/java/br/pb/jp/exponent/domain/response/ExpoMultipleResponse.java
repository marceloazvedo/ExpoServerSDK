package br.pb.jp.exponent.domain.response;

import java.util.Collection;
import java.util.List;

public class ExpoMultipleResponse {
	
	/**
	 * The response is a JSON object with two optional fields, data and errors. 
	 * If there is an error with the entire request, the HTTP status code will be 4xx or 5xx 
	 * and errors will be an array of error objects (usually just one).
	 * 
	 * source: https://docs.expo.io/versions/latest/guides/push-notifications#response-format
	 */	
	private Collection<ExpoResponseContent> data;
	private Collection<ExpoResponseContent> errors;

	public ExpoMultipleResponse() {}

	public Collection<ExpoResponseContent> getErrors() {
		return errors;
	}

	public void setErrors(List<ExpoResponseContent> errors) {
		this.errors = errors;
	}


	public Collection<ExpoResponseContent> getData() {
		return data;
	}

	public void setData(List<ExpoResponseContent> data) {
		this.data = data;
	}
	
}
