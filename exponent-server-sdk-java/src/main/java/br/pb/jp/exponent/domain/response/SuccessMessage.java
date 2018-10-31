package br.pb.jp.exponent.domain.response;

public class SuccessMessage {
	
	private String status;
	private String id;
	
	public SuccessMessage(String status, String id) {
		this.status = status;
		this.id = id;
	}

	public SuccessMessage() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
