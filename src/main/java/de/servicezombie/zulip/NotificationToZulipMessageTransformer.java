package de.servicezombie.zulip;

import de.servicezombie.rundeck.Notification;

/**
 * read from a template use template to create a zulip message;
 * 
 * @author chris
 *
 */
public class NotificationToZulipMessageTransformer {

	private String stream;
	private String topic;
	private String user;
	private String token;
	private String endpoint;
	
	public ZulipMessage transform(Notification notification) {
		final String message = readTemplate(notification);
		return new ZulipMessage(stream, topic, message, user, token, endpoint);
	}

	private String readTemplate(Notification notification) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
