package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class GameNightInstanceUserId implements Serializable {
	private static final long serialVersionUID = 42L;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "game_night_instance_id")
	private String gameNightInstanceId;
	
	public GameNightInstanceUserId() {}
	
	public GameNightInstanceUserId(int userId, String gameNightInstanceId) {
		super();
		this.userId = userId;
		this.gameNightInstanceId = gameNightInstanceId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getGameNightInstanceId() {
		return gameNightInstanceId;
	}
	public void setGameNightInstanceId(String gameNightInstanceId) {
		this.gameNightInstanceId = gameNightInstanceId;
	}	
}
