package com.neuromed.auth.service;
import com.neuromed.auth.entity.User;

import com.neuromed.auth.entity.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

	UserModel createUser(UserModel user);

	User readUser();
	UserModel readUserModel();

	UserModel updateUser(User user);
	
	void deleteUser();

	User getLoggedInUser();

	UserModel fetchUser(String userId);

	List<UserModel> getAllUsers();

	UserModel fetchUserByUsername(String username);
}
