package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration	//빈등록 ioc관리
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IOC 가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//auth허용 된 곳만 들어갈수있고 로그인 안되어있다면 /auth/loginForm 접속된다.
        http
    		.csrf().disable() //토근 비활성화 //ajax로 호출시에는 csrf토큰이 없기때문에 비활성화 상태로 변경
	        .authorizeHttpRequests()
	        .antMatchers("/","/auth/**","/js/**","/image/**")
	        .permitAll()
	        .anyRequest()
	        .authenticated()
        .and()
	        .formLogin()
	        .loginPage("/auth/loginForm")
	        .loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인 해준다.
	        .defaultSuccessUrl("/")
	        .failureUrl("/");//인증되면 해당으로 
	}

}
