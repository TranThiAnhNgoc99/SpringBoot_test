package com.example.spring01.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring01.DTO.UserDTO;
import com.example.spring01.config.JwtUtils;
import com.example.spring01.entity.MyUserDetails;
import com.example.spring01.exception.BadRequestException;
import com.example.spring01.exception.InternalServerException;
import com.example.spring01.exception.UnauthorizedException;
import com.example.spring01.model.LoginRequest;
import com.example.spring01.model.LoginResponseModel;
import com.example.spring01.model.UserInput;
import com.example.spring01.response.AllUserResponse;
import com.example.spring01.response.LoginResponse;
import com.example.spring01.response.UserResponse;
import com.example.spring01.services.UserService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiController {

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody( required = true) LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserid(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();

			return LoginResponse
					.success(new LoginResponseModel(jwt, myUserDetails.getId(), myUserDetails.getUsername()));
		} catch (AuthenticationException e) {
			if (e instanceof BadCredentialsException) {
				throw new UnauthorizedException("Unauthorized");
			}
			throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

	@PostMapping("/join")
	public UserResponse createUser(@Valid @RequestBody UserInput userInput,
			BindingResult result) {
		if (result.hasErrors()) {
			result.getAllErrors();
			String errorrMessage = "";
			for (int i = 0; i <result.getAllErrors().size(); i++) {
				String message 	= result.getAllErrors().get(i).getDefaultMessage();
				String field 	= result.getFieldErrors().get(i).getField();
				errorrMessage = errorrMessage + field + " : " + message + "; ";
			}
			throw new BadRequestException(errorrMessage);
		}
		UserDTO userDTO = userService.creatUserEntity(userInput);
		
		return UserResponse.success(userDTO);	
	}
	
	@GetMapping("/user")
	public AllUserResponse getAllUsers() throws UnauthorizedException{
		List<UserDTO> dtos = userService.getUsers();
		return AllUserResponse.success(dtos);
	}

	@GetMapping("/user/{id}")
	public UserResponse getUser(@Parameter(example = "1") @PathVariable("id") Long id) {
		UserDTO userDTO = userService.getUser(id);
		return UserResponse.success(userDTO);
	}
	
	@PutMapping("/user/{userId}")
	public UserResponse updateUser(@PathVariable Long userId,
			@Valid @RequestBody UserInput userInput,
			BindingResult result) {
		if (result.hasErrors()) {
			result.getAllErrors();
			String errorrMessage = "";
			for (int i = 0; i <result.getAllErrors().size(); i++) {
				String message 	= result.getAllErrors().get(i).getDefaultMessage();
				String field 	= result.getFieldErrors().get(i).getField();
				errorrMessage = errorrMessage + field + " : " + message + "; ";
			}
			throw new BadRequestException(errorrMessage);
		} 
		
		UserDTO userDTO = userService.updateUserEntity(userId, userInput);
		return UserResponse.success(userDTO);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
		return ResponseEntity.ok().body("Delete user succes sfully");
	}
	
}
