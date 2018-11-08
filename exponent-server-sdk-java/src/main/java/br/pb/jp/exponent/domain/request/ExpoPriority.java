package br.pb.jp.exponent.domain.request;

public enum ExpoPriority {
	
	DEFAULT("default"), NORMAL("normal"), HIGH("high");
	
	private String priority;
	
	private ExpoPriority(String priority) {
		this.priority = priority;
	}



	public String getPriority() {
		return this.priority;
	}

}
