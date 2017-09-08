package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "game_night_instance_board_game_vote")
public class GameNightInstanceBoardGameVote {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name = "system-uuid",
			strategy = "uuid"
			)
	@Column(name="id", updatable=false)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "game_night_instance_board_game_id")
	private GameNightInstanceBoardGame game;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "vote")
	private int vote;

	public GameNightInstanceBoardGameVote(String id, GameNightInstanceBoardGame game, User user, int vote) {
		super();
		this.id = id;
		this.game = game;
		this.user = user;
		this.vote = vote;
	}
	
	public GameNightInstanceBoardGameVote() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GameNightInstanceBoardGame getGame() {
		return game;
	}

	public void setGame(GameNightInstanceBoardGame game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}
	
}
