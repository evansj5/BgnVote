package viewmodel.user;

public class UserViewModel {
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String bggUsername;
	
	private boolean voteReminderEmail;
	
	private boolean rsvpReminderEmail;
	
	private boolean nominateReminderEmail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBggUsername() {
		return bggUsername;
	}

	public void setBggUsername(String bggUsername) {
		this.bggUsername = bggUsername;
	}

	public boolean isVoteReminderEmail() {
		return voteReminderEmail;
	}

	public void setVoteReminderEmail(boolean voteReminderEmail) {
		this.voteReminderEmail = voteReminderEmail;
	}

	public boolean isRsvpReminderEmail() {
		return rsvpReminderEmail;
	}

	public void setRsvpReminderEmail(boolean rsvpReminderEmail) {
		this.rsvpReminderEmail = rsvpReminderEmail;
	}

	public boolean isNominateReminderEmail() {
		return nominateReminderEmail;
	}

	public void setNominateReminderEmail(boolean nominateReminderEmail) {
		this.nominateReminderEmail = nominateReminderEmail;
	}
	
}
