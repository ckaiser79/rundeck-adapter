package de.servicezombie.rundeck;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/** . */
@XmlAccessorType(XmlAccessType.FIELD)
public class Execution {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String href;

	@XmlAttribute
	private String permalink;

	@XmlAttribute
	private String status;

	@XmlAttribute
	private String project;

	@XmlElement
	private String user;

	@XmlElement
	private String argstring;

	@XmlElement
	private String serverUUID;

	@XmlElement
	private String abortedby;

	// avoid conversion errors with date

	@XmlElement(name = "date-ended")
	private String dateEnded;

	@XmlElement(name = "date-started")
	private String dateStarted;

	@XmlElementWrapper(name = "failed-nodes")
	@XmlElement(name = "node", type = Node.class)
	private List<Node> failedNodes = new ArrayList<>();

	@XmlElementWrapper(name = "successfull-nodes")
	@XmlElement(name = "node", type = Node.class)
	private List<Node> successfulNodes = new ArrayList<>();

	@XmlElement
	private Job job;

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

	public void setDateEnded(String dateEnded) {
		this.dateEnded = dateEnded;
	}

	public String getDateEnded() {
		return dateEnded;
	}

	public void setDateStarted(String dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getDateStarted() {
		return dateStarted;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
