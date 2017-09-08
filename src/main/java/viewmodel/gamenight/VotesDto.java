package viewmodel.gamenight;

import java.util.List;

public class VotesDto {
	private String gameNightInstanceId;
	
	private List<VoteDto> votes;

	public VotesDto(String gameNightInstanceId, List<VoteDto> votes) {
		super();
		this.gameNightInstanceId = gameNightInstanceId;
		this.votes = votes;
	}
	
	public VotesDto() {}

	public String getGameNightInstanceId() {
		return gameNightInstanceId;
	}

	public void setGameNightInstanceId(String gameNightInstanceId) {
		this.gameNightInstanceId = gameNightInstanceId;
	}

	public List<VoteDto> getVotes() {
		return votes;
	}

	public void setVotes(List<VoteDto> votes) {
		this.votes = votes;
	}	
}
