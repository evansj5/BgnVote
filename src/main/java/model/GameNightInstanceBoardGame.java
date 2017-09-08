package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "game_night_instance_board_game")
public class GameNightInstanceBoardGame {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name = "system-uuid",
			strategy = "uuid"
			)
	@Column(name="id", updatable=false)
	private String id;
	
	@Column(name = "board_game_bgg_id")
	private String boardGameBggId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_night_instance_id")
	private GameNightInstance gameNightInstance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nominated_user_id")
	private User user;
	
	@Column(name = "average_vote")
	private Double averageVote;

	public GameNightInstanceBoardGame(String id, String boardGameBggId, GameNightInstance gameNightInstance,
			User user) {
		super();
		this.id = id;
		this.boardGameBggId = boardGameBggId;
		this.gameNightInstance = gameNightInstance;
		this.user = user;
	}
	
	public GameNightInstanceBoardGame() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBoardGameBggId() {
		return boardGameBggId;
	}

	public void setBoardGameBggId(String boardGameBggId) {
		this.boardGameBggId = boardGameBggId;
	}

	public GameNightInstance getGameNightInstance() {
		return gameNightInstance;
	}

	public void setGameNightInstance(GameNightInstance gameNightInstance) {
		this.gameNightInstance = gameNightInstance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getAverageVote() {
		return averageVote;
	}

	public void setAverageVote(double averageVote) {
		this.averageVote = averageVote;
	}
	
}
