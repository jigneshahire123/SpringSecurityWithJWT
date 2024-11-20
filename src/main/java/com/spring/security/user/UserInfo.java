package com.spring.security.user;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERINFO")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	private Set<Roles> roles;
	
	private Set<String> role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserInfo(int id, String username, String password, Set<String> role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role=role;
//		this.setRoles(roles);
	}

	public UserInfo() {
	}

	public Set< String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
	
//	public Set<Roles> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Roles> roles) {
//		this.roles = roles;
//	}
}
