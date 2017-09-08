package viewmodel.gamenight;

public class VoteDto {
	private String gameId;
	private int vote;
	
	public VoteDto(String gameId, int vote) {
		super();
		this.gameId = gameId;
		this.vote = vote;
	}
	
	public VoteDto() {}
	
	public String getGameId() {
		return gameId;
	}
	
	public void setGameId(String bggId) {
		this.gameId = bggId;
	}
	
	public int getVote() {
		return vote;
	}
	
	public void setVote(int vote) {
		this.vote = vote;
	}
}
