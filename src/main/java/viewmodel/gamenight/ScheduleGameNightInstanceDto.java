package viewmodel.gamenight;

import java.util.Date;

public class ScheduleGameNightInstanceDto {
	private Date startDate;
	private String gameNightId;
		
	public ScheduleGameNightInstanceDto() {
		super();
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getGameNightId() {
		return gameNightId;
	}
	public void setGameNightId(String gameNightId) {
		this.gameNightId = gameNightId;
	}
	
	
}
