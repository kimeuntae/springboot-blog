package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	//@PostMapping("/api/user")
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("save호출");
		user.setRole(RoleType.ADMIN);
		int result =  userService.joinSave(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result); //자바오브젝트를 JSON으로 변황하는데
		
	}
	
	//@PostMapping("/api/user")
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {//key=value , x-www-form-urlencoded
		int result =  userService.updateSave(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB값은 변경됬음
		// 세션은 변경되지 않음
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result); //자바오브젝트를 JSON으로 변황하는데
		
	}	
	
	//스프링 시큐리티 이용해서 로그인
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session) {
	 * System.out.println("로그인 호출됨");
	 * 
	 * User principal = userService.login(user); if(principal != null) {
	 * session.setAttribute("principal", principal); }
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK,1); //자바오브젝트를 JSON으로 변황하는데
	 * 
	 * }
	 */	
}
