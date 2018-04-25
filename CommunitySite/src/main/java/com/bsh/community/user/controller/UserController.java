package com.bsh.community.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsh.community.user.dao.UserDao;
import com.bsh.community.user.dto.UserDto;
import com.bsh.community.user.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@Autowired
	UserDao userDao;

	@Autowired
	UserService UserService;

	@RequestMapping("/loginForm")
	public String loginForm(Model model) {

		return "user/Login_View";
	}

	@RequestMapping("/signUpForm")
	public String signUpForm(Model model) {

		return "user/Sign_up";
	}

	@RequestMapping("/signUp")
	public String signUp(@Valid UserDto user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "user/Sign_up";
		} else {
			if (!user.getPassword().equals(user.getPassword2())) {
				model.addAttribute("passwdConfirm", "비밀번호가 틀립니다.");
				return "user/Sign_up";
			} else {
				if (UserService.signUp(user) == 0) {
					model.addAttribute("err", "사용할 수 없는 아이디 입니다.");
					return "user/Sign_up";
				}
			}
		}

		return "home";
	}

}
