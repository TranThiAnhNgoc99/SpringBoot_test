package com.example.spring01.model;

import java.io.File;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {
	@Schema(example = "Admin1234")
	@NotBlank(message = "IDUser is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter and one number")
	private String userid;
	
	@Schema(example = "Admin@1234")
	@NotBlank(message = "password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	private String password;

	@Schema(example = "Admin")
	@NotBlank(message = "username is required")
	private String username;
	
	@Schema(example = "Admin1234@gmail.com")
	@NotBlank(message = "email is required")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email")
	private String email;
	
	@Schema(example = "010-000-2345")
	@NotBlank(message = "phoneNumber is required")
	@Pattern(regexp = "(?:\\d{3}-){2}\\d{4}", message = "Invalid phone number please enter the correct format XXX-XXX-XXXX")
	private String phoneNumber;
	
	@Schema(example = "17-10, Dosan-daero 8-gil, Gangnam-gu, Seoul")
	@NotBlank(message = "address is required")
	private String address;
	
	//@NotBlank(message = "file is required")
	private MultipartFile  emoji;
}
