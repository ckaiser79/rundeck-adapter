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
public class Job {

	@XmlAttribute
	private String id;
	
	@XmlAttribute
	private String href;
	
	@XmlAttribute
	private String permalink;
	
	@XmlAttribute
	private Integer averageDuration;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String group;
	
	@XmlElement
	private String description;
	
	@XmlElementWrapper(name="options")
	@XmlElement(name = "option")
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

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", permalink=" + permalink + ", name=" + name + ", group=" + group + "]";
	}
	
	
}
