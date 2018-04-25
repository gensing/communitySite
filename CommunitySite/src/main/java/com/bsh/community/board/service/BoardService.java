package com.bsh.community.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bsh.community.board.dto.BoardDto;
import com.bsh.community.board.dto.ReplyDto;

public interface BoardService {

	/* 뷰 페이지 기능 */
	Map<String, Object> getBoard(BoardDto vo);

	Map<String, Object> getBoardList(BoardDto vo);

	Map<String, Object> getUpdateBoard(BoardDto vo);

	/* 게시판 처리 기능 */
	void insertBoard(BoardDto vo);

	void updateBoard(BoardDto vo);

	void deleteBoard(BoardDto vo);

	/* 댓글 처리 기능 */
	void insertReply(ReplyDto vo);

	void deleteReply(ReplyDto vo);

	/* 파일 다운로드 기능 */
	public void downView(HttpServletRequest req, HttpServletResponse resp, String name);
	
	public String uploadImg(MultipartFile file) ;
}
