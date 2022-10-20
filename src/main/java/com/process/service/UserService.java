package com.process.service;

import java.util.List;
import java.util.Optional;

import com.process.entity.User;

public interface UserService {
	
	Integer saveUser(User user);
	
	
	Optional<User> findByUserName(String userName);


	List<User> getAllusers();
	
	

}
