package de.servicezombie.rundeck;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/** . */
@XmlRootElement(name = "notification")
@XmlAccessorType (XmlAccessType.FIELD)
public class Notification {

	@XmlAttribute
	private String trigger;
	
	@XmlAttribute
	private String executionId;
	
	@XmlAttribute
	private String status;

	@XmlElementWrapper(name="executions")
	@XmlElement(name = "execution")
	private List<Execution> executions = new ArrayList<>();

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Execution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}

	@Override
	public String toString() {
		return "Notification [trigger=" + trigger + ", executionId=" + executionId + ", status=" + status
				+ ", executions=" + executions + "]";
	}

}
