package com.neuromed.auth.service;

import com.neuromed.auth.entity.User;
import com.neuromed.auth.entity.UserModel;
import com.neuromed.auth.exceptions.ItemExistsException;
import com.neuromed.auth.exceptions.ResourceNotFoundException;
import com.neuromed.auth.mapper.UserMapper;
import com.neuromed.auth.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@SuppressWarnings("unused")
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserModel createUser(UserModel user) {
		logger.info("createUser called for email={}", user != null ? user.getEmail() : "null");
		if (userRepository.existsByEmail(user.getEmail())) {
			logger.warn("Attempt to create user failed - email already exists={}", user.getEmail());
			throw new ItemExistsException("User is already register with email:"+user.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		newUser.setRoles(user.getRoles());
		User saved = userRepository.save(newUser);
		logger.info("User created successfully id={} email={}", saved.getId(), saved.getEmail());
		return UserMapper.toModel(saved);
	}

	@Override
	public UserModel readUserModel() {
		logger.debug("readUserModel called");
		Long userId = getLoggedInUser().getId();
		UserModel model = UserMapper.toModel(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for the id:"+userId)));
		logger.debug("readUserModel returning for id={}", userId);
		return model;
	}

	@Override
	public User readUser() {
		logger.debug("readUser called");
		Long userId = getLoggedInUser().getId();
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for the id:"+userId));
		logger.debug("readUser found id={}", userId);
		return user;
	}

	@Override
	public UserModel fetchUser(String userId) {
		logger.info("fetchUser called for userId={}", userId);
		User user = userRepository.findById(Long.parseLong(userId))
				.orElseThrow(() -> new ResourceNotFoundException("User not found for the id:" + userId));
		logger.debug("fetchUser found id={}", userId);
		return UserMapper.toModel(user);
	}

	@Override
	public UserModel updateUser(User user) {
		logger.info("updateUser called for principal id (will update logged in user)");
		User existingUser = readUser();
		existingUser.setId(existingUser.getId());
		existingUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : existingUser.getFirstName());
		existingUser.setLastName(user.getLastName() != null ? user.getLastName() : existingUser.getLastName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		User saved = userRepository.save(existingUser);
		logger.info("updateUser successful for id={}", saved.getId());
		return UserMapper.toModel(saved);
	}

	@Override
	public void deleteUser() {
		logger.info("deleteUser called for logged in user");
		User existingUser = readUser();
		userRepository.delete(existingUser);
		logger.info("deleteUser completed for id={}", existingUser.getId());
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("getLoggedInUser principal={}", authentication != null ? authentication.getName() : "null");
		String email = authentication.getName();
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email"+email));
		logger.debug("getLoggedInUser found email={}", email);
		return user;
	}

	@Override
	public List<UserModel> getAllUsers() {
		logger.info("getAllUsers called");
		List<UserModel> list = userRepository.findAll()
				.stream()
				.map(UserMapper::toModel)
				.collect(java.util.stream.Collectors.toList());
		logger.debug("getAllUsers returning {} users", list != null ? list.size() : 0);
		return list;
	}



	@Override
	public UserModel fetchUserByUsername(String username) {
		logger.info("fetchUserByUsername called for username={}", username);
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for email:" + username));
		return UserMapper.toModel(user);
	}

}
