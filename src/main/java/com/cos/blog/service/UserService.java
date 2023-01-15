package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@Transactional
	public int joinSave(User user) {
		try {
			String rawPassword = user.getPassword();
			String encPassword = encode.encode(rawPassword);
			System.out.println("user.getPassword() : " + user.getPassword()+"\n encPassword : " + encPassword);
			
			user.setPassword(encPassword);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("User Service 회원가입()" + e.getMessage());
		}
		return -1;
	}
	
	/*
	 * @Transactional(readOnly = true) // Select 할때 트랜잭션 시작 , 서비스 종료시에 트랜잭션 종료(정합성)
	 * public User login(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */	
	
	
}
