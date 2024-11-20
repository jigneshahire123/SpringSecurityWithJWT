package com.spring.security.user;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.userRepo.UserRepository;

@Service
public class UserInfoService implements UserDetailsService { 
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = userRepository.findByUsername(username);
		// Converting userDetail to UserDetails
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	} 


	public UserInfo saveUser(UserDto dto) {
        UserInfo entity = new UserInfo();
        entity.setRole(dto.getRole());
        entity.setusername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        
        UserInfo savedUser = userRepository.save(entity);
        BeanUtils.copyProperties(savedUser, dto);
        return savedUser;
    }


} 
