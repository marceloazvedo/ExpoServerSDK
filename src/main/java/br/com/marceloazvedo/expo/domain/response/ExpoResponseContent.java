package br.com.marceloazvedo.expo.domain.response;


/**
 * This class encapsulate all fields
 * be an error or be a success response
 *  
 * @author odravison
 *
 */
public class ExpoResponseContent {
	
	/**
	 * Fields filled only if is a not success response;
	 */	
	private String code;
	private String message;
	private ErrorDetail details;
	
	/**
	 * Fields filled in success or not success response 
	 */
	private String status;
	
	/**
	 * Fields only filled if is a success response
	 */
	private String id;
	
	public ExpoResponseContent(String code, String message, ErrorDetail details, String status, String id) {
		this.code = code;
		this.message = message;
		this.details = details;
		this.status = status;
		this.id = id;
	}

	public ExpoResponseContent() {}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorDetail getDetails() {
		return details;
	}

	public void setDetails(ErrorDetail details) {
		this.details = details;
	}
	
}
