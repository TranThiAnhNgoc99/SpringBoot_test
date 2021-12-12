package com.example.spring01.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring01.DTO.UserDTO;
import com.example.spring01.entity.MyUserDetails;
import com.example.spring01.entity.User;
import com.example.spring01.exception.BadRequestException;
import com.example.spring01.exception.NotFoundException;
import com.example.spring01.mapper.UserMapper;
import com.example.spring01.model.UserInput;
import com.example.spring01.repositories.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<User> user = userRepository.findByUserid(username);

	        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
	        return user.map(MyUserDetails::new).get();
	}

	@Override
	public UserDTO creatUserEntity(UserInput userInput) {
		Optional<User> user = userRepository.findByUserid(userInput.getUserid());
		if(user.isPresent()) {
			throw new BadRequestException("User has been exist");
		}
		User user2 = new User();
		try {
			userMapper.inputToEntity(userInput,user2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userMapper.entityToDto(userRepository.save(user2));
	}

	@Override
	public UserDTO updateUserEntity(Long userID, UserInput userInput) {
		Optional<User> uOptional = userRepository.findById(userID);
		if ( !uOptional.isPresent()) {
			throw new NotFoundException("This user is not found");
		}
		if (uOptional.get().getUsername() != userInput.getUsername()) {
			if (userRepository.findByUserid(userInput.getUsername()).isPresent()) {
				throw new BadRequestException("Username has been exist");
			}
		}
		try {
			userMapper.inputToEntity(userInput, uOptional.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		User user2 = userRepository.save(uOptional.get());
		return userMapper.entityToDto(user2);
	}

	@Override
	public void deleteUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new NotFoundException("This user is not found.");
		}
		userRepository.delete(user.get());		
	}

	@Override
	public UserDTO getUser(Long userId) {
		Optional<User> uOptional = userRepository.findById(userId);
		if (!uOptional.isPresent()) {
			throw new NotFoundException("This user is not found");
		}
		return userMapper.entityToDto(uOptional.get());
	}

	@Override
	public List<UserDTO> getUsers() {
		return userMapper.EntitiesToDtos((List<User>) userRepository.findAll());
	}

	
	

}
