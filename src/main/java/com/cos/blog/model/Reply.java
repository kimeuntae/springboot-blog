package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //Reply 클래스가 MySql에 테이블이 생성이 된다
public class Reply {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스 , 자동순번생성
	
	@Column(nullable = false,length = 200)
	private String content;

	@ManyToOne // Many = Reply , Board = One
	@JoinColumn(name="boardId")
	private Board board; // 게시판 아이디	 
	
	@ManyToOne // Many = Reply , User = One
	@JoinColumn(name="userId")
	private User user; // 아이디	 
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createData;
}
