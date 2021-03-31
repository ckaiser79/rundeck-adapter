package de.servicezombie.zulip;

public class ZulipMessage {

	private final String stream;
	private final String topic;
	private final String message;
	private final String user;
	private final String token;
	private final String endpoint;
	
	
	public ZulipMessage(String stream, String topic, String message, String user, String token, String endpoint) {
		this.topic = topic;
		this.stream = stream;
		this.message = message;
		this.user = user;
		this.token = token;	
		this.endpoint = endpoint;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getStream() {
		return stream;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String toJson() {
		return "";
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getToken() {
		return token;
	}

}
