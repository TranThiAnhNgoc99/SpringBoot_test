package com.example.spring01.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponseModel {
	@Schema(example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE2MzQ3MTQyNjcsImV4cCI6MTYzNDgwMDY2N30.y3-scJdgQCRi1U2Wz0EkvVq3jrNukwkxHUuxT27dHMkPq9f9XQhQxbB3P1HJ8afZgxnDByLfYX7MULbJ1d5SBQ", required = true)
	private String token;
	@Schema(example = "Bearer")
	private String type = "Bearer";
	@Schema(example = "1", required = true)
	private Long id;
	@Schema(example = "admin1", required = true)
	private String username;
	
	public LoginResponseModel(String token, Long id, String username) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
	}
	
}
