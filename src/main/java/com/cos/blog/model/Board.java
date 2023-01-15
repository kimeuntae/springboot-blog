package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //Board 클래스가 MySql에 테이블이 생성이 된다
public class Board {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스 , 자동순번생성
	
	@Column(nullable = false,length = 100)
	private String title; // 아이디
	
	
	@Lob //대용량 데이터
	private String content;


	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board , User = One
	@JoinColumn(name="userId")
	private User user; // 아이디
	
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //DB컬럼을 만들지 않는다. //Select용 //Reply에 board객체 이름
	private List<Reply> reply; //조회시 reply 데이터 가져오기
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createData;
}
