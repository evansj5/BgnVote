package viewmodel.gamenight;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.GameNight;
import model.GameNightInstanceState;

public class GameNightInstanceViewModel {
	private String id;
	private String gameNightId;
	private Date date;
	private GameNightInstanceState state;
	private boolean nominated;
	private boolean coming;
	private boolean rsvpd;
	private boolean voted;

	
	
	public GameNightInstanceViewModel(String id, String gameNightId, Date date, GameNightInstanceState state,
			boolean nominated, boolean coming, boolean rsvpd, boolean voted) {
		super();
		this.id = id;
		this.gameNightId = gameNightId;
		this.date = date;
		this.state = state;
		this.nominated = nominated;
		this.coming = coming;
		this.rsvpd = rsvpd;
		this.voted = voted;
	}

	public GameNightInstanceViewModel() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGameNightId() {
		return gameNightId;
	}

	public void setGameNightId(String gameNightId) {
		this.gameNightId = gameNightId;
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

	@JsonProperty(value="nominated")
	public boolean hasNominated() {
		return nominated;
	}

	public void setNominated(boolean nominated) {
		this.nominated = nominated;
	}

	public boolean isComing() {
		return coming;
	}

	public void setComing(boolean coming) {
		this.coming = coming;
	}

	@JsonProperty(value="rsvpd")
	public boolean hasRsvpd() {
		return rsvpd;
	}

	public void setRsvpd(boolean rsvpd) {
		this.rsvpd = rsvpd;
	}

	@JsonProperty(value="voted")
	public boolean hasVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	
	
}
