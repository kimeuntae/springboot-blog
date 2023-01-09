package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

// dadta를 리턴해주는 
@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	/* 삭제 */
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return  "삭제에 실패하였습니다.";
		}
		
		return "id 삭제되었습니다.";
	}
	
	// save 함수는 Id를 전달하지 않으면 insert를 해주고
	// save 함수는 Id를 전달하면 해당 Id에 대한 데이터가 있으면 update를 해주고
	// save 함수는 Id를 전달하면 해당 Id에 대한 데이터가 없으면 insert 를 한다.
	/* 수정 */
	@Transactional // 함수 종료시에 자동 커밋 됨
	@PostMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id , @RequestBody User requestUser) {
		//user.setRole(RoleType.ADMIN);
		
		/*데이터 없을경우 오류 처리*/
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());		
		//userRepository.save(user);
		
		//변경감지 하여 업데이트
		//더티 체킹
		return null;
	}
	
	/* 리스트 조회 */
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}	
	
	/* 페이지 조회 */
	//http://localhost:8000/blog/dummy/user/page?page=0
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent(); 
		return pagingUser;
	}
	
	/* 상세조회 */ 
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user.setRole(RoleType.ADMIN);
		
		/* Null 리턴*/		
		/*
		 * User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		 * 
		 * @Override public User get() { return new User(); } });
		 */

		/* Throw 리턴*/
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다.");
			};
		});
		
		// 변환 json 으로 변환해줌
		// 스프링부트 = MessageConverter 라는 애가 응답시에 자동 작동
		return user;
	}
	
	/* 저장 */
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.ADMIN);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
}
