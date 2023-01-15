package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다.
//@Repository 생갹 가능
public interface UserRepository  extends JpaRepository<User, Integer>{ //User테이블에 키는 Integer Type
	Optional<User> findByUsername(String username);
}


//JPA Namig 전략
//Select * fromuser Where username = ? and password = ?;
//User findByUsernameAndPassword(String username , String password);

//@Query(value = "Select * fromuser Where username = ?1 and password = ?2", nativeQuery = true)
//User login(String username , String password);
