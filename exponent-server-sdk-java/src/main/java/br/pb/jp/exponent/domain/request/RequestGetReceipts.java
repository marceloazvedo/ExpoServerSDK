package br.pb.jp.exponent.domain.request;

import java.util.List;

public class RequestGetReceipts {
	
	private List<String> ids;
	
	public RequestGetReceipts() {}

	public RequestGetReceipts(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	
	
	
	
}
