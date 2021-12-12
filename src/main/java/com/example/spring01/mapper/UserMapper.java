package com.example.spring01.mapper;

import java.io.IOException;
import java.util.List;

import com.example.spring01.DTO.UserDTO;
import com.example.spring01.entity.User;
import com.example.spring01.model.UserInput;

public interface UserMapper {
	public UserDTO entityToDto(User entity);
	public void inputToEntity(UserInput input, User entity) throws IOException;
	List<UserDTO> EntitiesToDtos(List<User> entities);
}
