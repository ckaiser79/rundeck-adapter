package de.servicezombie.rundeck;

import java.util.ArrayList;
import java.util.List;

public class Job {

	private String id;
	private String href;
	private String permalink;
	private Integer averageDuration;
	
	private String name;
	private String group;
	private String description;
	
	// avoid conversion errors with date
	
	private String dateEnded;
	private String dateStarted;
	
	private List<Option> options = new ArrayList<>();

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

	public Integer getAverageDuration() {
		return averageDuration;
	}

	public void setAverageDuration(Integer averageDuration) {
		this.averageDuration = averageDuration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(String dateEnded) {
		this.dateEnded = dateEnded;
	}

	public String getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(String dateStarted) {
		this.dateStarted = dateStarted;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", permalink=" + permalink + ", name=" + name + ", group=" + group + ", dateEnded="
				+ dateEnded + ", dateStarted=" + dateStarted + "]";
	}
	
	
}
