package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 MySql에 테이블이 생성이 된다
@DynamicInsert //null값은 sql에서 빠진다
@DynamicUpdate
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스 , 자동순번생성
	
	@Column(nullable = false,length = 30)
	private String username; // 아이디
	
	@Column(nullable = false,length = 100) //123456=>해쉬 비번 암호화
	private String password;
	
	@Column(nullable = false,length = 30)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋음//
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createData;
	
}
