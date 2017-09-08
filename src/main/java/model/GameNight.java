package model;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "game_night")
public class GameNight {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name = "system-uuid",
			strategy = "uuid"
			)
	@Column(name="id", updatable=false)
	private String id;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "REPEAT_DAYS")
	private int repeatDays;
	
	@OneToMany(mappedBy = "gameNight")
	private List<GameNightInstance> instances;	
	
	public GameNight(String id, Date startDate) {
		super();
		this.id = id;
		this.startDate = startDate;
	}
	
	public GameNight() {}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getRepeatDays() {
		return repeatDays;
	}

	public void setRepeatDays(int repeatDays) {
		this.repeatDays = repeatDays;
	}	
}
