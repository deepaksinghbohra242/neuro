package com.neuromed.auth.exceptions;

import java.time.LocalDateTime;
import java.util.*;
import com.neuromed.auth.entity.ErrorObject;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorObject> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		errorObject.setMessage("Invalid username or password.");
		errorObject.setErrorTime( LocalDateTime.now());
		return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObject.setMessage("Invalid parameter type provided. Please verify your request parameters.");
		errorObject.setErrorTime( LocalDateTime.now());
		return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorObject> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		errorObject.setMessage("Access denied. You do not have permission to perform this action.");
		errorObject.setErrorTime( LocalDateTime.now());
		return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorObject.setMessage("An unexpected error occurred. Please try again later.");
		errorObject.setErrorTime( LocalDateTime.now());
		return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ItemExistsException.class)
	public ResponseEntity<ErrorObject> handleItemExistsException(ItemExistsException ex, WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.CONFLICT.value());
		errorObject.setMessage("The item already exists. Duplicate entries are not allowed.");
		errorObject.setErrorTime( LocalDateTime.now());
		return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("statusCode", HttpStatus.BAD_REQUEST.value());
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getDefaultMessage())
				.toList();
		body.put("messages", errors);
		body.put("timestamp", new Date());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
