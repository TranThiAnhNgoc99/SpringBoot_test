package com.example.spring01.services;

import java.util.List;

import com.example.spring01.DTO.UserDTO;
import com.example.spring01.model.UserInput;

public interface UserService {

	UserDTO creatUserEntity(UserInput userInput);

	UserDTO updateUserEntity(Long userID, UserInput userInput);

	void deleteUser(Long userId);

	UserDTO getUser(Long userId);

	List<UserDTO> getUsers();
}
