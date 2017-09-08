package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BggThingResponseModel {
	@XmlAttribute
	private String id;
	
	@XmlElement
	private String image;
	
	@XmlElement(name = "name")
	private List<BggThingNameElement> names;
	
	@XmlElement
	private String thumbnail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<BggThingNameElement> getNames() {
		return names;
	}

	public void setNames(List<BggThingNameElement> names) {
		this.names = names;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}


