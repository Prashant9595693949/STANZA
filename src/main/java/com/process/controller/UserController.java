package com.process.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.process.entity.User;
import com.process.entity.UserRequest;
import com.process.entity.UserResponse;
import com.process.service.UserService;
import com.process.util.JWTUtil;

@RestController
@RequestMapping("/Manager")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private JWTUtil util;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		
		Integer id= userservice.saveUser(user);
		String Body="User'"+id+"' Saved";
		
		//return new ResponseEntity<String>(Body,HttpStatus.OK); 
		return ResponseEntity.ok(Body);
	}
	
	
	@GetMapping("/get")
	public List<User> getallusers() {
		return userservice.getAllusers();
	}
		
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request){
		
		 authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
		
		String token=util.generateToken(request.getUserName());
		
			return ResponseEntity.ok(new UserResponse(token,"Token is generated SuccessFully")); 	
		 
		//return ResponseEntity.ok(new UserResponse(token));
	}

}

