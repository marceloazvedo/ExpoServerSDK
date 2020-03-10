package br.com.marceloazvedo.expo.domain.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ExpoBaseException extends Exception {
	
	private static final long serialVersionUID = -6884007450105771704L;
	
	private String code;
	private HttpStatus status;
	private List<ExpoAPIError> errors;
	
	
	
	public ExpoBaseException(String message, String code, HttpStatus status) {
		super(message);
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<ExpoAPIError> getErrors() {
		return errors;
	}

	public void setErrors(List<ExpoAPIError> errors) {
		this.errors = errors;
	}
	

}
