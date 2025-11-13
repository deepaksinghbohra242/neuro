package com.neuromed.appointments.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserModel {

	private Long id;

	@NotBlank(message = "First Name should not be empty")
	private String firstName;

	@NotBlank(message = "Last Name should not be empty")
	private String lastName;

	@NotNull(message = "Email should not be empty")
	@Email(message = "Enter a valid email")
	private String email;

	@NotNull(message = "roles should not be empty")
	private Set<String> roles;

	@NotNull(message = "Password should not be empty")
	@Size(min = 5, message = "Password should be atleast 5 characters")
	private String password;
	
	private Long age = 0L;

}
