package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "game_night_user")
public class GameNightUser {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name = "system-uuid",
			strategy = "uuid"
			)
	@Column(name="id", updatable=false)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_night_id")
	private GameNight gameNight;
	
	@Column(name = "is_owner")
	private boolean owner;

	public GameNightUser(String id, User user, GameNight gameNight, boolean owner) {
		super();
		this.id = id;
		this.user = user;
		this.gameNight = gameNight;
		this.owner = owner;
	}
	
	public GameNightUser() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GameNight getGameNight() {
		return gameNight;
	}

	public void setGameNight(GameNight gameNight) {
		this.gameNight = gameNight;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}	
}
