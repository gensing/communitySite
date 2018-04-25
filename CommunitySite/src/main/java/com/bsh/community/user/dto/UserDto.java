package com.bsh.community.user.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserDto{
	@Email
	private String id;
	
	@Size(min=4, max=12)
	private String password;
	
	private String password2;

	private String roles;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
}
