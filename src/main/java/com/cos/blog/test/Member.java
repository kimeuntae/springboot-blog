package com.cos.blog.test;

import org.springframework.stereotype.Controller;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@AllArgsConstructor

@Data
@NoArgsConstructor
public class Member {
	String id;
	String name;
	String password;
	String email;
	
	@Builder
	public Member(String id, String name, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
}


