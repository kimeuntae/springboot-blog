package com.cos.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 인증이 안된 사용자들이 출입할 수 있는 경로르르 /auth/** 허용
// 그냥 주소가 / 이면 index.hjsp 허용
// static이하에 있는 /js/** , /css/**, /image/**

@Controller
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Value("${cos.key}")
	private String cosKey;

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}	
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		//post 방식으로 key=value 데이터 요청(카카오쪽으로)
		//Retrofit2
		//OkHttp
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "72e96e00dd0c0e2ccf640d61c166239a");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity< MultiValueMap<String, String>>kakaoTokenRequest =
				new HttpEntity<>(params,headers);
				
		// Http 요청하기 - Post방식으로 - 그리고 response
		ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class
				);
		
		//Gson , Json Simple , ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken authToken = null;		
		try {
			authToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeader 
		HttpHeaders headers2 = new HttpHeaders();
		headers.add("Authorization", "Bearer "+ authToken.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity< MultiValueMap<String, String>>kakaoProfileRequest =
				new HttpEntity<>(params,headers);
				
		// Http 요청하기 - Post방식으로 - 그리고 response
		ResponseEntity<String> response2 = rt.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest,
					String.class
				);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoprofile = null;		
		try {
			kakaoprofile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("kakaoprofile ID : " + kakaoprofile.getId());
		System.out.println("kakaoprofile 이메일 : " + kakaoprofile.getKakao_account().getEmail());
		
		System.out.println("블로그 유저네임 : " + kakaoprofile.getKakao_account().getEmail() +"_" + kakaoprofile.getId());
		System.out.println("블로그 이메일 : " + kakaoprofile.getKakao_account().getEmail());
		UUID garbagePassword = UUID.randomUUID(); 
		System.out.println("블로그 패스워드 : " + cosKey);
		
		User kakaoUser = User.builder()
				.username(kakaoprofile.getKakao_account().getEmail() +"_" + kakaoprofile.getId())
				.password(cosKey)
				.email(kakaoprofile.getKakao_account().getEmail())
				.role(RoleType.ADMIN)
				.oauth("kakao")
				.build();
		
		// 가입자 혹은 비가입자 체크 해서 처리
		User user = userservice.fineUser(kakaoUser.getUsername());
		
		//회원가입
		if(user.getUsername() == null) {
			userservice.joinSave(kakaoUser);
		}
		
		//로그인 처리
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		return "redirect:/";
	}
	

}

