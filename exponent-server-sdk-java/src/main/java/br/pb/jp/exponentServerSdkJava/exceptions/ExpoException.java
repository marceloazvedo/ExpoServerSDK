package br.pb.jp.exponentServerSdkJava.exceptions;

import org.apache.http.HttpStatus;

public class ExpoException extends Exception {
	
	private static final long serialVersionUID = -6884007450105771704L;
	
	private String code;
	private HttpStatus status;
	
	public ExpoException(String message, String code, HttpStatus status) {
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
	
	
		
	

}
