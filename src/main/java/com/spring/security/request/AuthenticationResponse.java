package com.spring.security.request;

import java.util.List;

public class AuthenticationResponse {
	private String jwt;
	private List<String> role;
	String username;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AuthenticationResponse(String jwt, List<String> role, String username) {
		super();
		this.jwt = jwt;
		this.role = role;
		this.username = username;
	}
}

