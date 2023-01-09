package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*데이터 넘길경우 @RestController*/
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombok() {
		/*lombok Test 생성자 및 Setter Getter 테스트*/
		Member m = Member.builder().email("test").build();
		return "lombok Test :" + m.getId() + "," +  m.getName() + "," +  m.getEmail();
	}
	
	/*Get요청*/
	@GetMapping("/test/get")
	public String get(Member m) {
		return "get :" + m.getId() + "," +  m.getName();
	}

	/*Post요청*/
	@PostMapping("/test/post")
	public String post(@RequestBody  String text) { //application/text , application/json
		
		//return "post :" + m.getId() + "," +  m.getName();
		return "post :" + text;
	}
	
	/*Put요청*/
	@PutMapping("/test/put")
	public String put(@RequestBody  Member m) { //application/text , application/json
		
		return "put :" + m.getId() + "," +  m.getName();
	}
	
	/*Delete요청*/
	@DeleteMapping("/test/delete")
	public String delete(@RequestBody  Member m) { //application/text , application/json
		
		return "delete :" + m.getId() + "," +  m.getName();
	}	
}
