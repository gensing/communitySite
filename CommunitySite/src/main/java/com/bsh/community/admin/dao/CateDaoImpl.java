package com.bsh.community.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsh.community.admin.dto.CateDto;
import com.bsh.community.board.dto.BoardDto;

@Repository("CateDao")
public class CateDaoImpl implements CateDao{

	@Autowired
	private SqlSession sqlSession;

	private String namespace = "com.bsh.community.admin.dao.CateDao";

	public List<String> getCateBoxList() {
		return sqlSession.selectList(namespace + ".getCateBoxList");
	}

	public List<CateDto> getCateList(String boxName) {
		return sqlSession.selectList(namespace + ".getCateList", boxName);
	}

	public void setCate(CateDto cateVo) {
		sqlSession.insert(namespace + ".setCate", cateVo);
	}

	public void delCate(CateDto cateVo) {
		sqlSession.delete(namespace + ".delCate", cateVo);
	}
	
	public void updateCate(CateDto cateVo){
		sqlSession.delete(namespace + ".updateCate", cateVo);
	}
	
	public void setBox(CateDto cateVo) {
		sqlSession.insert(namespace + ".setBox", cateVo);
	}

	public void delBox(CateDto cateVo) {
		sqlSession.delete(namespace + ".delBox", cateVo);
	}
	
	public void updateBox(CateDto cateVo){
		sqlSession.delete(namespace + ".updateBox", cateVo);
	}
	
	public List<BoardDto> getHomeList(CateDto cateVo){
		return sqlSession.selectList(namespace + ".getHomeList", cateVo);
	}
	
	public int confirmBox(CateDto cateVo){
		return sqlSession.selectOne(namespace + ".confirmBox", cateVo);
	}
	
	public int confirmCate(CateDto cateVo){
		return sqlSession.selectOne(namespace + ".confirmCate", cateVo);
	}

}
