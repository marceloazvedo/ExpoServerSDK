package br.pb.jp.exponentServerSdkJava.exceptions;

import org.apache.http.HttpStatus;

public class MessageRateExceededException extends ExpoException {

	private static final long serialVersionUID = 5219722334291130989L;

	public MessageRateExceededException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
}
