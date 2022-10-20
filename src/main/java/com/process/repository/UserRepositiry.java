package com.process.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.process.entity.User;
@Repository
public interface UserRepositiry extends JpaRepository<User, Integer> {
	

	Optional<User> findByUserName(String userName);

}
