package com.college.yi.ecsite.front.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.exception.DuplicateEmailException;
import com.college.yi.ecsite.front.form.RegisterForm;
import com.college.yi.ecsite.front.repository.UserMapper;

@Service
public class RegisterService {
	
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public RegisterService(UserMapper userMapper) {
		this.userMapper = userMapper;
		}
	
	public void registerUser(RegisterForm form) {
		if (userMapper.findByEmail(form.getEmail()) != null) {
			throw new DuplicateEmailException("DuplicateEmail");
		}
		
		User user = new User();
		user.setLastName(form.getLastName());
		user.setFirstName(form.getFirstName());
		user.setLastNameKana(form.getLastNameKana());
		user.setFirstNameKana(form.getFirstNameKana());
		user.setEmail(form.getEmail());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		
		userMapper.insert(user);
	}
}
