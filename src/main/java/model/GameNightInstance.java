package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "game_night_instance")
public class GameNightInstance {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name = "system-uuid",
			strategy = "uuid"
			)
	@Column(name="id", updatable=false)
	private String id;
	
	@Column(name = "date")
	private Date date;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state")
	private GameNightInstanceState state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_night_id")
	private GameNight gameNight;
	
	@OneToMany(mappedBy = "gameNightInstance")
	private List<GameNightInstanceBoardGame> nominatedBoardGames;

	public GameNightInstance(String id, String gameNightId, Date date, GameNightInstanceState state) {
		super();
		GameNight gameNight = new GameNight();
		gameNight.setId(gameNightId);
		this.gameNight = new GameNight();
		this.gameNight.setId(gameNightId);
		this.id = id;
		this.date = date;
		this.state = state;
	}
	
	public GameNightInstance() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public GameNight getGameNight() {
		return gameNight;
	}

	public void setGameNight(GameNight gameNight) {
		this.gameNight = gameNight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public GameNightInstanceState getState() {
		return state;
	}

	public void setState(GameNightInstanceState state) {
		this.state = state;
	}	
}
