package de.servicezombie.zulip;

import java.util.Arrays;

public class ZulipRequest {

	private ZulipType type = ZulipType.STREAM;

	private String[] to;
	private String topic;
	private String content;
	private String jobName;
	
	private final ZulipEndpoint endpoint;

	public ZulipRequest(ZulipEndpoint endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public String toString() {
		return "ZulipRequest ["+ "jobName=" + jobName + "type=" + type + ", to=" + Arrays.toString(to) + ", topic=" + topic + ", content="
				+ (content != null ? content.length() : null) + ", endpoint=" + endpoint + "]";
	}

	public ZulipType getType() {
		return type;
	}

	public void setType(ZulipType type) {
		this.type = type;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ZulipEndpoint getEndpoint() {
		return endpoint;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobName() {
		return jobName;
	}

}
