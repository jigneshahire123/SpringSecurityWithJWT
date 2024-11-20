package com.spring.security.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails {
	private static final long serialVersionUID = -5028756820456364262L;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoDetails(UserInfo userInfo) {
		this.username = userInfo.getUsername();
		this.password = userInfo.getPassword();
		this.authorities = userInfo.getRole().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
//		for (Roles user: userInfo.getRoles()) {
//			auth.add(new SimpleGrantedAuthority(user.getName().toUpperCase()));	
//		}
//		this.authorities=authorities;

//		this.authorities = Arrays.stream(userInfo.getRole().split(",")) 
//				.map(SimpleGrantedAuthority::new) 
//				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
