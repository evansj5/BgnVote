package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "user_roles")
public class UserRoleModel {
	@Id	
	@SequenceGenerator(name="user_roles_user_role_id_seq",
						sequenceName="user_roles_user_role_id_seq",
						allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY,
					generator="user_roles_user_role_id_seq")
	@Column(name = "user_role_id")
	private int id;
	
	private String username;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public UserRoleModel () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
