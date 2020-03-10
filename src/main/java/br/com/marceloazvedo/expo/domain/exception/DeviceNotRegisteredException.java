package br.pb.jp.exponent.domain.exception;

import org.springframework.http.HttpStatus;

public class DeviceNotRegisteredException extends ExpoBaseException{

	private static final long serialVersionUID = 7399589103518215167L;

	public DeviceNotRegisteredException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
	
}
