package de.servicezombie.rundeck.rest;

import java.util.ArrayList;
import java.util.List;

public class Notification {

	private String trigger;
	private String executionId;
	private String status;

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
