package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Transactional(readOnly = true)
	public User fineUser(String usename) {
		User user = userRepository.findByUsername(usename).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
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
	
	@Transactional
	public int updateSave(User requestUser) {
		try {
			// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고 , 영속화된 User 오브젝트를 수정
			// select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
			// 영속화된 오브젝트를 변경하면 자동으로DB에 update문을 날려주거든요
			User persistance = userRepository.findById(requestUser.getId()).orElseThrow(()->{
					return new IllegalArgumentException("회원찾기실패");
			});
			
			//서버 공격에 대한 조치
			if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
				String rawPassword = requestUser.getPassword();
				String encPassword = encode.encode(rawPassword);
				persistance.setPassword(encPassword);
				persistance.setEmail(requestUser.getEmail());
			}
			// 영속화된 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
					
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("User Service 회원수정()" + e.getMessage());
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
