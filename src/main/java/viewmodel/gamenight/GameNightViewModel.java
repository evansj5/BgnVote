package viewmodel.gamenight;

import java.util.Date;
import java.util.List;
import java.util.Set;

import viewmodel.game.GameViewModel;

public class GameNightViewModel {
	private String id;
	
	private Date startDate;
	
	private int repeatDays;
	
	private List<Integer> invitedUsers;
	
	private boolean owned;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getRepeatDays() {
		return repeatDays;
	}

	public void setRepeatDays(int repeatDays) {
		this.repeatDays = repeatDays;
	}

	public List<Integer> getInvitedUsers() {
		return invitedUsers;
	}

	public void setInvitedUsers(List<Integer> invitedUsers) {
		this.invitedUsers = invitedUsers;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}
}
