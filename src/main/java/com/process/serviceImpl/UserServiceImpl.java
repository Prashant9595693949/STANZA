package com.process.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.stereotype.Service;

import com.process.entity.User;
import com.process.repository.UserRepositiry;
import com.process.service.UserService;
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
	
	@Autowired
	private UserRepositiry userrepo;
	
	@Autowired 
	private BCryptPasswordEncoder PasswordEncoder;

	
	
	@Override
	public Integer saveUser(User user) {
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		return userrepo.save(user).getId();
	}

	@Override
	public List<User> getAllusers() {
		return userrepo.findAll();
	}

	//get user by user name
	@Override
	public Optional<User> findByUserName(String userName) {
		return userrepo.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> data=findByUserName(userName);
		if(data.isEmpty())
			throw new UsernameNotFoundException("user not Exist");
		User user=data.get();
			return new org.springframework.security.core.userdetails.User(userName,user.getPassword(),new ArrayList<>());
			
	}

}
