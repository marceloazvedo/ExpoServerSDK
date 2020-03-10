package br.pb.jp.exponent.domain.exception;

import org.springframework.http.HttpStatus;

public class MessageTooBigException extends ExpoBaseException {
	
	private static final long serialVersionUID = -2222885070551382576L;

	public MessageTooBigException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}

}
