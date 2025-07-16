package com.jwt.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.model.JwtRequest;
import com.jwt.example.model.JwtResponse;
import com.jwt.example.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtHelper helper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getEmail(), request.getPassword());
		
		UserDetails userDetails =  userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails.getUsername());
		
		JwtResponse response = new JwtResponse();
		response.setJwtToken(token);
		response.setUserName(userDetails.getUsername());
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
	}
	
	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid user");
		}
	}
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid";
	}
	
	

}
