package viewmodel.gamenight;

import java.util.List;

public class GameNightInstanceGameResultDto {
	private String name;
	private List<Integer> votes;
	private double averageVote;
	
	public GameNightInstanceGameResultDto() {}

	public List<Integer> getVotes() {
		return votes;
	}

	public void setVotes(List<Integer> votes) {
		this.votes = votes;
	}

	public double getAverageVote() {
		return averageVote;
	}

	public void setAverageVote(double averageVote) {
		this.averageVote = averageVote;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
