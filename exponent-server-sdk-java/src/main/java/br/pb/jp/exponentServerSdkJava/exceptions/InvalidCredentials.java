package br.pb.jp.exponentServerSdkJava.exceptions;

import org.apache.http.HttpStatus;

public class InvalidCredentials extends ExpoException {
	
	private static final long serialVersionUID = 7738011890795427853L;

	public InvalidCredentials (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
	
	
	
	
}
