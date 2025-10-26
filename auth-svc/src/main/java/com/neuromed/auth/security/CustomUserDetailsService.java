package com.neuromed.auth.security;

import java.util.List;
import java.util.stream.Collectors;

import com.neuromed.auth.entity.User;
import com.neuromed.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User existingUser = userRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found for the email:" + email));
		// Assuming getRoles() returns a list of role names
		List<GrantedAuthority> authorities = existingUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(
				existingUser.getEmail(),
				existingUser.getPassword(),
				authorities);
	}

}
