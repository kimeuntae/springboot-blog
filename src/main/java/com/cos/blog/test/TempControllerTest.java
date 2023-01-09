package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*파일 넘길경우 @Controller*/
@Controller 
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		
		//파일리턴 기본경로  /src/main/resources/static
		// /src/main/resources/static 정적 파일만 가능
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		
		//파일리턴 기본경로  /src/main/resources/static
		return "/a.png";
	}
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		
		//파일리턴 기본경로 jsp webapp 밑에서만 가능
		return "/test";
	}
}
