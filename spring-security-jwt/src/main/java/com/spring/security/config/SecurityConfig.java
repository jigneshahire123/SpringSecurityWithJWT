package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.security.authfilter.JwtAuthFilter;
import com.spring.security.user.UserInfoService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;

	// Configuring HttpSecurity
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
//		        .authorizeHttpRequests(req -> req.requestMatchers("/auth/public/**").permitAll()
//		        		 						 .requestMatchers("/swagger-ui**").permitAll()
//		        								 .requestMatchers("/auth/protected/**").authenticated()
//		        								 .requestMatchers("/h2").permitAll()
//		        								 .anyRequest().authenticated()
//		        					  )
				.authorizeHttpRequests(req -> req
//				 		  req.requestMatchers("/auth/public/**").permitAll()
// 						 .requestMatchers("/swagger-ui**").permitAll()
						.requestMatchers("/auth/protected/**").authenticated()
//						 .requestMatchers("/h2").permitAll()
						.anyRequest().permitAll())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
//		        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no use of
																										// cookies
				.authenticationProvider(authenticationProvider())
//		        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoService();
	}

	// Password Encoding
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		// database connection authentication
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
