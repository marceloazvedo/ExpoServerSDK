package br.pb.jp.exponentServerSdkJava.exceptions;

import org.apache.http.HttpStatus;

public class MessageTooBigException extends ExpoException {
	
	private static final long serialVersionUID = -2222885070551382576L;

	public MessageTooBigException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}

}
