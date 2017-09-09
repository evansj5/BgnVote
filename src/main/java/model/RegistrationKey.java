package model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "registration_key")
public class RegistrationKey {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name = "system-uuid",
			strategy = "uuid"
			)
	@Column(name="registration_key", updatable=false)
	private String registrationKey;	
	
	private boolean used;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private UserRole userRole;
	
	@Column(name = "expire_date")
	private Date expireDate;
	
	private String email;
	
	public RegistrationKey() {}

	public String getRegistrationKey() {
		return registrationKey;
	}

	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
