package br.pb.jp.exponent.domain.response;

public class ErrorDetail {

	private String error;
	
	public ErrorDetail() {}

	public ErrorDetail(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
