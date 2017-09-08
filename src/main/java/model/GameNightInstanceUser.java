package model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "game_night_instance_user")
public class GameNightInstanceUser {
	@EmbeddedId
	private GameNightInstanceUserId id;
	
	@Column(name = "has_rsvpd")
	private boolean rsvpd;
	
	@Column(name = "is_coming")
	private boolean coming;
	
	@Column(name = "has_nominated")
	private boolean nominated;
	
	@Column(name = "has_voted")
	private boolean voted;
	
	public GameNightInstanceUser() {
		this.rsvpd = false;
		this.coming = false;
		this.nominated = false;
		this.voted = false;
	}
	
	public GameNightInstanceUser(GameNightInstanceUserId id, boolean rsvpd, boolean coming,
			boolean nominated, boolean voted) {
		super();
		this.id = id;
		this.rsvpd = rsvpd;
		this.coming = coming;
		this.nominated = nominated;
		this.voted = voted;
	}	

	public GameNightInstanceUserId getId() {
		return id;
	}

	public void setId(GameNightInstanceUserId id) {
		this.id = id;
	}

	public boolean hasRsvpd() {
		return rsvpd;
	}

	public void setRsvpd(boolean rsvpd) {
		this.rsvpd = rsvpd;
	}

	public boolean isComing() {
		return coming;
	}

	public void setComing(boolean coming) {
		this.coming = coming;
	}

	public boolean hasNominated() {
		return nominated;
	}

	public void setNominated(boolean nominated) {
		this.nominated = nominated;
	}

	public boolean hasVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	
	
}
