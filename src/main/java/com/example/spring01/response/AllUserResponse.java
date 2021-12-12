package com.example.spring01.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.example.spring01.DTO.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AllUserResponse {
	@Schema(example = "200")
	int status;
	@Nullable
	private final List<UserDTO>  data;
	@Schema(example = "Get List Users Successfully!")
	String message;

	private AllUserResponse(@Nullable List<UserDTO>  data, int status, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	public static AllUserResponse success(List<UserDTO> data) {
		return new AllUserResponse(data, HttpStatus.OK.value(), null);
	}
	
}
