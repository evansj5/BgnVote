package model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity(name="users")
public class User implements UserDetails{
	@Id	
	@SequenceGenerator(name="USERS_id_seq",
						sequenceName="USERS_id_seq",
						allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY,
					generator="USERS_id_seq")
	@Column(name="id", updatable=false)
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="bgg_username")
	private String bggUsername;
	
	@Column(name = "vote_reminder_email")
	private boolean voteReminderEmail;
	
	@Column(name = "rsvp_reminder_email")
	private boolean rsvpReminderEmail;
	
	@Column(name = "nominate_reminder_email")
	private boolean nominateReminderEmail;
	
	private String username;
	
	private String password;
	
	private String email;
	
	@OneToMany(mappedBy = "user")
	private List<GameNightUser> gameNights;
	
	@OneToMany(mappedBy = "user")
	private List<GameNightInstanceBoardGame> nominatedBoardGames;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBggUsername() {
		return bggUsername;
	}

	public void setBggUsername(String bggUsername) {
		this.bggUsername = bggUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GameNightUser> getGameNights() {
		return gameNights;
	}

	public void setGameNights(List<GameNightUser> gameNights) {
		this.gameNights = gameNights;
	}

	public List<GameNightInstanceBoardGame> getNominatedBoardGames() {
		return nominatedBoardGames;
	}

	public void setNominatedBoardGames(List<GameNightInstanceBoardGame> nominatedBoardGames) {
		this.nominatedBoardGames = nominatedBoardGames;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return authorities;
	}
	
	@Transient
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
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
