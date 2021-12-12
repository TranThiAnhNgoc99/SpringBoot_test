package com.example.spring01.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.spring01.DTO.UserDTO;
import com.example.spring01.entity.User;
import com.example.spring01.model.UserInput;

@Component
public class UserMapperImpl implements UserMapper{

	private static final Logger log = LoggerFactory.getLogger(UserMapperImpl.class);

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO entityToDto(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setUserid(entity.getUserid());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setAddress(entity.getAddress());
		return dto;
	}

	@Override
	public void inputToEntity(UserInput input, User entity) throws IOException {
		entity.setUserid(input.getUserid());
		entity.setPassword(passwordEncoder.encode(input.getPassword()));
		entity.setUsername(input.getUsername());
		entity.setEmail(input.getEmail());
		entity.setPhoneNumber(input.getPhoneNumber());
		entity.setAddress(input.getAddress());
		entity.setEmoji(input.getEmoji().getBytes());
	}

	@Override
	public List<UserDTO> EntitiesToDtos(List<User> entities) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		for(User entity : entities) {
			UserDTO dto = new UserDTO();
			dto.setId(entity.getId());
			dto.setUserid(entity.getUserid());
			dto.setUsername(entity.getUsername());
			dto.setEmail(entity.getEmail());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setAddress(entity.getAddress());
			list.add(dto);
		}
		return list;
	}

}
