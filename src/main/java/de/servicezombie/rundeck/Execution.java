package de.servicezombie.rundeck;

import java.util.ArrayList;
import java.util.List;

public class Execution {

	private String id;
	private String href;
	private String permalink;
	private String status;
	private String project;
	private String user;
	private String argstring;
	private String serverUUID;
	private String abortedby;
	
	private List<Node> failedNodes = new ArrayList<>();
	private List<Node> successfulNodes = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getArgstring() {
		return argstring;
	}
	public void setArgstring(String argstring) {
		this.argstring = argstring;
	}
	public String getServerUUID() {
		return serverUUID;
	}
	public void setServerUUID(String serverUUID) {
		this.serverUUID = serverUUID;
	}
	public String getAbortedby() {
		return abortedby;
	}
	public void setAbortedby(String abortedby) {
		this.abortedby = abortedby;
	}
	public List<Node> getFailedNodes() {
		return failedNodes;
	}
	public void setFailedNodes(List<Node> failedNodes) {
		this.failedNodes = failedNodes;
	}
	public List<Node> getSuccessfulNodes() {
		return successfulNodes;
	}
	public void setSuccessfulNodes(List<Node> successfulNodes) {
		this.successfulNodes = successfulNodes;
	}
	@Override
	public String toString() {
		return "Execution [id=" + id + ", permalink=" + permalink + ", status=" + status + "]";
	}
	
	
}
