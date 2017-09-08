package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="items")
@XmlAccessorType(XmlAccessType.FIELD)
public class BggGamesResponseModel {
	@XmlAttribute(name="totalitems")
	private int totalItems;
	
	@XmlElement(name="item")
	private List<BggGameResponseModel> items;
	
	public List<BggGameResponseModel> getItems() {
		return items;
	}

	public void setItems(List<BggGameResponseModel> items) {
		this.items = items;
	}	
	
	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public BggGamesResponseModel() {}
}
