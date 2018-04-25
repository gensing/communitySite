package com.bsh.community.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsh.community.board.dto.BoardDto;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	private String namespace = "com.bsh.community.board.dao.BoardDao";

	public BoardDto getBoard(BoardDto vo) {
		return sqlSession.selectOne(namespace + ".getBoard", vo);
	};

	public List<BoardDto> getBoardList(BoardDto vo) {
		return sqlSession.selectList(namespace + ".getBoardList", vo);
	};

	public int insertBoard(BoardDto vo) {
		return sqlSession.insert(namespace + ".insertBoard", vo);
	};

	public void deleteBoard(BoardDto vo) {
		sqlSession.delete(namespace + ".deleteBoard", vo);
	};

	public void updateBoard(BoardDto vo) {
		sqlSession.update(namespace + ".updateBoard", vo);
	};

	public int getBBSCnt(BoardDto vo) {
		return sqlSession.selectOne(namespace + ".getBBSCnt",vo);
	};
	
	public int countUp(BoardDto vo) {
		return sqlSession.insert(namespace + ".countUp", vo);
	};
	
	public String getBoardListTitle(BoardDto vo) {
		return sqlSession.selectOne(namespace + ".getBoardListTitle", vo);
	};
}
