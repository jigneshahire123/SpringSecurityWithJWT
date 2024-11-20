package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(/* exclude = DataSourceAutoConfiguration.class */)
public class SpringSecurity3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity3Application.class, args);
	}

}
