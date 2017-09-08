package model;

import javax.xml.bind.annotation.XmlAccessorType;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class BggThingsResponseModel {
	
	@XmlElement(name = "item")
	private List<BggThingResponseModel> things;

	public List<BggThingResponseModel> getThings() {
		return things;
	}

	public void setThings(List<BggThingResponseModel> things) {
		this.things = things;
	}	
}
