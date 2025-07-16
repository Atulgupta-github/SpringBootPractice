package com.jwt.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwt.example.model.User;

@Service
public class UserService {

	private List<User> users = new ArrayList<>();
	
	public UserService() {
		users.add(new User(UUID.randomUUID().toString(), "Atul Gupta","atul@gmail.com"));
		users.add(new User(UUID.randomUUID().toString(), "Nitin Gupta","atul@gmail.com"));
		users.add(new User(UUID.randomUUID().toString(), "Mayank Gupta","atul@gmail.com"));
		users.add(new User(UUID.randomUUID().toString(), "Raj Gupta","atul@gmail.com"));
	}
	
	public List<User> getAllUsers(){
		return this.users;
	}
}
