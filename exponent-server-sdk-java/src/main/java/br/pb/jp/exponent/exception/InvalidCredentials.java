package br.pb.jp.exponent.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends ExpoBaseException {
	
	private static final long serialVersionUID = 7738011890795427853L;

	public InvalidCredentials (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
	
	
	
	
}
