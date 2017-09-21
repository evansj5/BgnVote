package viewmodel.gamenight;

import java.util.Map;
import java.util.TreeMap;

public class GameNightInstanceGameResultDto {
	private String name;
	private Map<Integer, Integer> votes;
	private double averageVote;
	
	public GameNightInstanceGameResultDto() {}

	public Map<Integer, Integer> getVotes() {
		return votes;
	}

	public void setVotes(Map<Integer, Integer> votes) {
		this.votes = new TreeMap<>(votes);
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
