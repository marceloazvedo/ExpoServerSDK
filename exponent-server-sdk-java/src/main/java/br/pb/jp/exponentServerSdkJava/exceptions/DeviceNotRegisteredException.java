package br.pb.jp.exponentServerSdkJava.exceptions;

import org.apache.http.HttpStatus;

public class DeviceNotRegisteredException extends ExpoException{

	private static final long serialVersionUID = 7399589103518215167L;

	public DeviceNotRegisteredException (String message, String code, HttpStatus status) {
		super(message, code, status);
		
	}
	
}
