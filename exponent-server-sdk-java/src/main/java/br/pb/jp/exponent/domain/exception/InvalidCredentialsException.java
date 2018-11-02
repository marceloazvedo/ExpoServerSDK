package br.pb.jp.exponent.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends ExpoBaseException {
	
	private static final long serialVersionUID = 7738011890795427853L;

	public InvalidCredentialsException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
	
	
	
	
}
