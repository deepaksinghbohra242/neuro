package com.neuromed.auth.controller;


import com.neuromed.auth.entity.AuthModel;
import com.neuromed.auth.entity.JwtResponse;
import com.neuromed.auth.entity.User;
import com.neuromed.auth.entity.UserModel;
import com.neuromed.auth.security.CustomUserDetailsService;
import com.neuromed.auth.service.UserService;
import com.neuromed.auth.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
		name = "Auth REST APIs",
		description = "APIs for user authentication and registration"
)
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Operation(summary = "Login", description = "Authenticate user and return JWT token")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Login successful, JWT token returned",
					content = @Content(schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(responseCode = "401", description = "Invalid credentials"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws UsernameNotFoundException {
		authenticate(authModel.getEmail(), authModel.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return new ResponseEntity<>(new JwtResponse(token, HttpStatus.OK.value(), "JWT token generated." , LocalDateTime.now()), HttpStatus.OK);
	}
	
	private void authenticate(String email, String password) throws UsernameNotFoundException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new UsernameNotFoundException("User is disabled");
		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("Invalid User or Password Credentials");
		}

	}

	@Operation(summary = "Register", description = "Register a new user")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "User registered successfully",
					content = @Content(schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})

	@PostMapping("/register")
	public ResponseEntity<UserModel> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/fetch")
	public ResponseEntity<UserModel> fetchUserDetails(@RequestHeader("neuromed-correlation-id") String correlationId,
													  @RequestParam("userId") String userId) {
		return new ResponseEntity<>(userService.fetchUser(userId), HttpStatus.CREATED);
	}
}


















