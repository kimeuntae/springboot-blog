package com.cos.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

	@Test
	public void name() {
		boolean encPassword = new BCryptPasswordEncoder().matches("1234", "$2a$10$whFtjWzUW0vKCrFl5Tc.LegjkOx46U0qBvIvkSFyJCq35Qi2xumXy");
		System.out.println(encPassword);
	}
}
