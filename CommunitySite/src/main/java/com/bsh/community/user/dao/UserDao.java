package com.bsh.community.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsh.community.user.dto.UserDto;

@Repository("userDao")
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	private String namespace = "com.bsh.community.user.dao.UserDao";

	public UserDto getUser(String username) {

		return sqlSession.selectOne(namespace + ".getUser", username);
	}
	
	public void insertUser(UserDto user){
		sqlSession.insert(namespace + ".insertUser", user);
		
	}

}
