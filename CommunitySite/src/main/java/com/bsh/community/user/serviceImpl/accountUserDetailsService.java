package com.bsh.community.user.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bsh.community.user.dao.JdbcUserDao;
import com.bsh.community.user.dto.UserDto;

@Service
public class accountUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JdbcUserDao dao = new JdbcUserDao();
		UserDto userDto = dao.getUser(username);
		
		if (userDto.getId() == null){
			throw new UsernameNotFoundException("유저를 찾을 수 없습니다. : " + username);
		}

		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(userDto.getRoles()));
		User userDetails = new User(userDto.getId(), userDto.getPassword(), roles);

		return userDetails;
	}
}
