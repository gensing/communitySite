package com.bsh.community.admin.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsh.community.admin.dao.CateDao;
import com.bsh.community.admin.dto.CateDto;

@Service("cateServiceImpl")
public class CateServiceImpl {

	@Autowired
	CateDao cateDao;

	public ArrayList<Object> getCate() {
		ArrayList<Object> Category = new ArrayList<>();

		for (String boxName : cateDao.getCateBoxList()) {
			Map<String, Object> boxInfo = new HashMap<>();
			boxInfo.put("boxName", boxName);
			boxInfo.put("cateList", cateDao.getCateList(boxName));
			Category.add(boxInfo);
		}

		return Category;
	}

	public String setCate(CateDto cateVo) {
		String err = "";
		if (cateDao.confirmCate(cateVo) != 1) {
			cateDao.setCate(cateVo);
		} else {
			err = "해당 박스에 중복된 카테고리가 존재합니다.";
		}
		return err;
	}

	public String setBox(CateDto cateVo) {
		String err = "";
		if (cateDao.confirmBox(cateVo) != 1) {
			cateDao.setBox(cateVo);
		} else {
			err = "중복된 박스가 존재합니다.";
		}
		return err;
	}

	public void delCate(CateDto cateVo) {
		System.out.println("delcate:1");
		cateDao.delCate(cateVo);
		System.out.println("delcate:2");

	}

	public void delBox(CateDto cateVo) {
		cateDao.delBox(cateVo);
	}

	public ArrayList<Object> getHomeList() {
		ArrayList<Object> Category = new ArrayList<>();

		for (String boxName : cateDao.getCateBoxList()) {
			for (CateDto temp : (ArrayList<CateDto>) cateDao.getCateList(boxName)) {
				Map<String, Object> tt = new HashMap<>();
				tt.put("cate", temp.getCate_name());
				tt.put("boardList", cateDao.getHomeList(temp));
				Category.add(tt);
			}
			;

		}
		return Category;
	}

}
