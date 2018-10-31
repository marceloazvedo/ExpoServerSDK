package br.pb.jp.exponent.domain.response;

import java.util.List;

public class Response {
	
	private List<SuccessMessage> data;

	public Response(List<SuccessMessage> data) {
		this.data = data;
	}

	public Response() {}

	public List<SuccessMessage> getData() {
		return data;
	}

	public void setData(List<SuccessMessage> data) {
		this.data = data;
	}
	
}
