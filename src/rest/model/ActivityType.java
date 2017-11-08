package rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="activity_type")
public enum ActivityType {
	SPORT("Sport"),
	SOCIAL("Social"),
	ACADEMIC("Academic"),
	WORK("Work"),
	CULTURE("Culture");
	
	
	private String type;
	
	ActivityType(String type){
		this.type = type;
	}
	
}
