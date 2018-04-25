package com.bsh.community.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsh.community.board.dto.BoardDto;
import com.bsh.community.board.dto.FileDto;

@Repository
public class FileDao {
	@Autowired
	private SqlSession sqlSession;

	private String namespace = "com.bsh.community.board.dao.FileDao";

	public List<FileDto> getFileList(BoardDto vo) {
		return sqlSession.selectList(namespace + ".getFileList", vo);
	};

	public void insertFile(FileDto vo) {
		sqlSession.insert(namespace + ".insertFile", vo);
	};
	
	public void delFile(FileDto vo){
		sqlSession.delete(namespace + ".delFile", vo);
		
	}
	
	public String getFileRealName(String name){
		return sqlSession.selectOne(namespace +".getFileRealName", name);
	}

}
