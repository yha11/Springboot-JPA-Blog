package com.cos.blog.service;

import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class DummyService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void updateUser(int id,  User requestUser) {
		//여기서 트렌젝션 시작
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		//파인드해서 가져오면 임마를 캐쉬에 저장함..
		System.out.println("before update user=>"+user);
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//가져온애를 바꾸면 아직까진 캐쉬에 저장함 디비 반영 안함
		System.out.println("after update user=>"+ user);
	}
	//이 서비스가 끝나면 트랜젝션이 종료됨 그러면서 캐쉬에 저장되었던 user를 디비에 반영함
	

	
	
	public User findUser(int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		return user;
	}
}
