package com.example.spring01.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Admin1234")
	private String userid;
	
	@Schema(example = "Admin")
	private String username;
	
	@Schema(example = "Admin1234@gmail.com")
	private String email;
	
	@Schema(example = "010-000-234")
	private String phoneNumber;
	
	@Schema(example = "17-10, Dosan-daero 8-gil, Gangnam-gu, Seoul")
	private String address;
	
}

