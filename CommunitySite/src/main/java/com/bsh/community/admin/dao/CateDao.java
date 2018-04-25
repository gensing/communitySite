package com.bsh.community.admin.dao;

import java.util.List;

import com.bsh.community.admin.dto.CateDto;
import com.bsh.community.board.dto.BoardDto;

public interface CateDao {
	public List<String> getCateBoxList() ;

	public List<CateDto> getCateList(String boxName);

	public void setCate(CateDto cateVo);

	public void delCate(CateDto cateVo);
	
	public void updateCate(CateDto cateVo);

	public void setBox(CateDto cateVo);

	public void delBox(CateDto cateVo);
	
	public void updateBox(CateDto cateVo);
	
	public List<BoardDto> getHomeList(CateDto cateVo);
	
	public int confirmBox(CateDto cateVo);
	
	public int confirmCate(CateDto cateVo);
}
