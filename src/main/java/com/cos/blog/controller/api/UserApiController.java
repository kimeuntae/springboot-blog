package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	//@PostMapping("/api/user")
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("save호출");
		user.setRole(RoleType.ADMIN);
		int result =  userService.joinSave(user);
		return new ResponseDto<Integer>(HttpStatus.OK,result); //자바오브젝트를 JSON으로 변황하는데
		
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
