package com.spring.security.user;

import java.util.Set;

public class UserDto {
	private long userId;
	private String username;
	private String password;
	private Set<String> role;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public UserDto(long userId, String username, String password, Set<String> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = roles;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}

}