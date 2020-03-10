package br.com.marceloazvedo.expo.domain.response;

public class ErrorDetail {

	private String error;
	private String fault;
	private String sentAt;
	
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

	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}
	
}
