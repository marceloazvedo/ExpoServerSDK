package br.com.marceloazvedo.expo.domain.exception;

public class ExpoAPIError {
	
	private String code;
	private String message;
	
	public ExpoAPIError() {}
	
	public ExpoAPIError(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
