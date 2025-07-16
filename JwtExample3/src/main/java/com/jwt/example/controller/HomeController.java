package com.jwt.example.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.jwt.example.model.User;
import com.jwt.example.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	
	@Autowired
	private UserService userService;
	//http://localhost:8081/home/user
	
	@GetExchange("/user")
	public List<User> getUser() {
		return this.userService.getAllUsers();
	}
	
	@GetMapping("/loginUserName")
	public String getLoggedInUserName(Principal principal) {
		return principal.getName();
	}

}
