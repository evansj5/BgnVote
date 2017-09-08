package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



@Entity(name="users")
public class User {
	@Id	
	@SequenceGenerator(name="USERS_id_seq",
						sequenceName="USERS_id_seq",
						allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
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
	
	private String username;
	
	@OneToMany(mappedBy = "user")
	private List<GameNightUser> gameNights;
	
	@OneToMany(mappedBy = "user")
	private List<GameNightInstanceBoardGame> nominatedBoardGames;

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
}
