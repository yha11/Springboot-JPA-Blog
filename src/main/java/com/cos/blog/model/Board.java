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
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터에 사용
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨. (용량이 커짐)
	
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One // 한 명의 유저는 여러 글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. 그래서 FK를 사용. 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy : 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 칼럼을 만들지 마세요. // OneToMany의 fetch 디폴트는 LAZY
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
