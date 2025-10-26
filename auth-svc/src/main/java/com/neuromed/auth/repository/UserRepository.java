package com.neuromed.auth.repository;

import java.util.Optional;

import com.neuromed.auth.entity.User;
import com.neuromed.auth.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
