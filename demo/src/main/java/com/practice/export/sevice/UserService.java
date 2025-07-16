package com.practice.export.sevice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.export.payload.UserDTO;
import com.practice.model.User;
import com.practice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private String name="Atul Gupta";
	private String address = "Noida";
	private Integer age=27;
	private String designation="Software Engineer";
	
	
	public User createUser(){
		
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setAddress(address);
		user.setDesignation(designation);
		userRepo.save(user);

		return user;
		
	}
	
	public UserDTO entToUserDTO(User user) {
		UserDTO dto = this.modelMapper.map(user, UserDTO.class);
		return dto;
	}
}
