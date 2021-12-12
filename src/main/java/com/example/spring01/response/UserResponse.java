package com.example.spring01.response;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.example.spring01.DTO.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponse {
	@Schema(example = "200")
	int status;
	@Nullable
	private final UserDTO  data;
	@Schema(example = "Get User Successfully!")
	String message;

	private UserResponse(@Nullable UserDTO  data, int status, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	public static UserResponse success(UserDTO data) {
		return new UserResponse(data, HttpStatus.OK.value(), null);
	}

}
