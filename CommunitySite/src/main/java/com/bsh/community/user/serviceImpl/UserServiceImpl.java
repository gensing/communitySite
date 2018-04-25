package com.bsh.community.user.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bsh.community.user.dao.UserDao;
import com.bsh.community.user.dto.UserDto;
import com.bsh.community.user.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	public int signUp (UserDto user){
		if(userDao.getUser(user.getId()) != null)
			return 0;
		user.setPassword(bcrypt.encode(user.getPassword()));
		userDao.insertUser(user);
		return 1;
	}
}
