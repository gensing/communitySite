package com.bsh.community.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsh.community.board.dto.BoardDto;
import com.bsh.community.board.dto.ReplyDto;

@Repository
public class ReplyDao {

	@Autowired
	private SqlSession sqlSession;

	private String namespace = "com.bsh.community.board.dao.ReplyDao";

	public List<ReplyDto> getReplyList(BoardDto vo) {
		return sqlSession.selectList(namespace + ".getReplyList", vo);
	};

	public void insertReply(ReplyDto vo) {
		sqlSession.insert(namespace + ".insertReply",vo);
	};

	public void deleteReply(ReplyDto vo) {
		sqlSession.delete(namespace + ".deleteReply", vo);
	};

}
