package br.pb.jp.exponent.exception;

import org.springframework.http.HttpStatus;

public class MessageRateExceededException extends ExpoBaseException {

	private static final long serialVersionUID = 5219722334291130989L;

	public MessageRateExceededException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
}
