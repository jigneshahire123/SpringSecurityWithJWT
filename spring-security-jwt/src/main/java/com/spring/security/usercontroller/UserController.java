package com.spring.security.usercontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.jwtutil.JwtService;
import com.spring.security.request.AuthenticationRequest;
import com.spring.security.request.AuthenticationResponse;
import com.spring.security.user.UserDto;
import com.spring.security.user.UserInfo;
import com.spring.security.user.UserInfoService;

@RestController
@RequestMapping("/auth")
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class.getName());

	@Autowired
	private UserInfoService userDetailsService;

	@Autowired
	private JwtService jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * non-protected endpoints
	 */

	@GetMapping("/public/hello")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.ok("From public endpoint");
	}

	@PostMapping("/public/register")
	public ResponseEntity<UserInfo> addNewUser(@RequestBody UserDto userInfo) {
		UserInfo saveUser = userDetailsService.saveUser(userInfo);
		return new ResponseEntity<>(saveUser, HttpStatus.OK);
	}

	@PostMapping("/public/login") // login for authenticated user
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {
		Authentication authenticate;
		try {
			// authenticate user
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException badCredentialsException) {
			LOG.error("Incorrect username or password");
			throw badCredentialsException;
		}

		//setting context
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		//getting userdetails
		final UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
		// getting roles 
		final List<String> role = authenticate.getAuthorities().stream().map(i -> i.getAuthority())
				.toList();

		// create token for authenticated user
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		return ResponseEntity.ok(new AuthenticationResponse(jwt, role, userDetails.getUsername()));
	}
	
	

	/*
	 * protected endpoints
	 */

	@GetMapping("/protected/user/userProfile")
//	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<?> userProfile() {
		
		//another approach for checking the roles of user accessing the resource
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> list = authentication.getAuthorities().stream().map(r->r.getAuthority()).toList();
		if(list.contains("ADMIN")) {
			return new ResponseEntity<>("Welcome to User Profile", HttpStatus.OK);
		}
		return new ResponseEntity<>("User does not having appropriate credentials", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/protected/admin/adminProfile")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String adminProfile() {
		return "Welcome to Admin Profile";
	}

}
