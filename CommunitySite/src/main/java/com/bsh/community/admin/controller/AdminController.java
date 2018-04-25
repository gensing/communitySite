package com.bsh.community.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsh.community.admin.dto.CateDto;
import com.bsh.community.admin.serviceImpl.CateServiceImpl;

@Controller
public class AdminController {
	@Autowired
	CateServiceImpl cateServiceImpl;

	@RequestMapping(value = "/getCate", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Object> getCate(Model model) {
		return cateServiceImpl.getCate();
	}
	
	@RequestMapping(value = "/admin/adminPage", method = RequestMethod.GET)
	public String adminPage(Model model, CateDto vo) {
		model.addAttribute("err", vo.getErr());
		return "admin/adminPage";
	}
	
	@RequestMapping(value = "/admin/addBox/{boxName}", method = RequestMethod.GET)
	public String addBox(Model model, CateDto vo) {
		model.addAttribute("err", cateServiceImpl.setBox(vo));
		return "redirect:/admin/adminPage";
	}
	
	@RequestMapping(value = "/admin/addCate/{cate_name}/{boxName}", method = RequestMethod.GET)
	public String addCate(Model model, CateDto vo) {
		model.addAttribute("err", cateServiceImpl.setCate(vo));
		return "redirect:/admin/adminPage";
	}
	
	@RequestMapping(value = "/admin/delBox/{boxName}", method = RequestMethod.GET)
	public String delBox(Model model, CateDto vo) {
		cateServiceImpl.delBox(vo);
		return "redirect:/admin/adminPage";
	}
	
	@RequestMapping(value = "/admin/delCate/{seq}", method = RequestMethod.GET)
	public String delCate(Model model, CateDto vo) {
		cateServiceImpl.delCate(vo);
		return "redirect:/admin/adminPage";
	}

}
