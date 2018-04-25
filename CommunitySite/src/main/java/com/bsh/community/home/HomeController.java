package com.bsh.community.home;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bsh.community.admin.serviceImpl.CateServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	CateServiceImpl cateServiceImpl;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("homeList", cateServiceImpl.getHomeList());
		return "home";
	}

	@RequestMapping(value = "/test")
	public String test(Locale locale, Model model) throws IOException {
		logger.info("test.", locale);

		return "test/test";
	}

	@RequestMapping(value = "/test2")
	public String test2(Locale locale, Model model) throws IOException {
		logger.info("test2.", locale);

		return "test/test2";
	}

	@RequestMapping(value = "/denidePage")
	public String denidePage(Locale locale, Model model)  {
		logger.info("denidePage.", locale);

		return "denidePage";
	}

}
