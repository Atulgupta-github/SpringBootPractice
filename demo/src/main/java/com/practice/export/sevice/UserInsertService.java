package com.practice.export.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInsertService {
	
	@Autowired
	private UserService userService;
	
	public void inserUser() {
		userService.createUser();
	}

}
