package com.neuromed.auth.controller;

import com.neuromed.auth.entity.User;
import com.neuromed.auth.entity.UserModel;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
		name = "User REST APIs",
		description = "APIs for user profile management"
)
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Operation(summary = "Get User Profile", description = "Fetch the authenticated user's profile")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User profile fetched successfully",
					content = @Content(schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized")
	})

	@GetMapping("/profile")
	public ResponseEntity<UserModel> readUser() {
		return new ResponseEntity<>(userService.readUserModel(), HttpStatus.OK);
	}

	@Operation(summary = "Get User By JWT Token", description = "Extract username from JWT and return the user")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User fetched successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})

	@GetMapping("/by-token")
	public ResponseEntity<UserModel> getByToken(
			@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
			@RequestParam(value = "token", required = false) String tokenParam) {

		String token = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
		} else if (tokenParam != null && !tokenParam.isBlank()) {
			token = tokenParam;
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserModel userModel = userService.fetchUserByUsername(username);
		return ResponseEntity.ok(userModel);
	}

	@Operation(summary = "Update User Profile", description = "Update the authenticated user's profile")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User profile updated successfully",
					content = @Content(schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	//@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PutMapping("/profile")
	public ResponseEntity<UserModel> updateUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> deleteUser() {
		userService.deleteUser();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
