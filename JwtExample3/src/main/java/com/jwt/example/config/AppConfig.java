package com.jwt.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1= User.builder().username("Atul").password(passwordEncoder().encode("Atul@1234")).roles("ADMIN").build();
		UserDetails user2= User.builder().username("Nitin").password(passwordEncoder().encode("Nitin@1234")).roles("ADMIN").build();
		UserDetails user3= User.builder().username("Mayank").password(passwordEncoder().encode("Mayank@1234")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user1,user2,user3);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
