package com.bsh.community.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bsh.community.board.dto.BoardDto;
import com.bsh.community.board.dto.ReplyDto;
import com.bsh.community.board.service.BoardService;
import com.bsh.community.home.HomeController;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	/* 게시판 처리 */
	@RequestMapping("/Board")
	public String getBoard(BoardDto vo, Model model) {
		model.addAllAttributes(boardService.getBoard(vo));
		return "bbs/Board_View";
	}

	@RequestMapping("/BoardList")
	public String getBoardList(BoardDto vo, Model model) {
		model.addAllAttributes(boardService.getBoardList(vo));
		return "bbs/Board_List";
	}

	@RequestMapping("/updateBoardForm")
	public String updateBoardView(BoardDto vo, Model model) {
		model.addAllAttributes(boardService.getUpdateBoard(vo));
		model.addAttribute("cate", vo.getCate());

		return "bbs/Board_Update";
	}

	@RequestMapping("/insertBoardForm")
	public String insertBoardForm(BoardDto vo, Model model) {
		model.addAttribute("cate", vo.getCate());
		return "bbs/Board_Insert";
	}

	@RequestMapping(value = "/insertBoard")
	public String insertBoard(@Valid BoardDto vo, BindingResult bindingResult, Model model) {
		model.addAttribute("cate", vo.getCate());

		if (bindingResult.hasErrors()) {
			model.addAttribute("re", vo);
			return "bbs/Board_Insert";
		}

		boardService.insertBoard(vo);
		return "redirect:BoardList";
	}

	@RequestMapping("/deleteBoard")
	public String deleteBoard(BoardDto vo, Model model) {
		boardService.deleteBoard(vo);
		model.addAttribute("cate", vo.getCate());
		return "redirect:BoardList";
	}

	@RequestMapping("/updateBoard")
	public String updateBoard(BoardDto vo, Model model) {
		boardService.updateBoard(vo);
		model.addAttribute("seq", vo.getSeq());
		return "redirect:Board";
	}

	/* 댓글 처리 */
	@RequestMapping("/insertReply")
	public String insertReply(ReplyDto vo, Model model) {
		boardService.insertReply(vo);
		model.addAttribute("seq", vo.getbId());
		return "redirect:Board";
	}

	@RequestMapping("/deleteReply")
	public String deleteReply(ReplyDto vo, Model model) {
		boardService.deleteReply(vo);
		model.addAttribute("seq", vo.getbId());
		return "redirect:Board";
	}

	/* 다운로드 처리 */
	@RequestMapping("/download/{file}")
	void download(HttpServletRequest req, HttpServletResponse resp, @PathVariable String file) {
		boardService.downView(req, resp, file);
	}

	@RequestMapping(value = "/bbs/{cate}", method = RequestMethod.GET)
	public String moveBBS(Model model, BoardDto vo) {
		model.addAllAttributes(boardService.getBoardList(vo));
		return "bbs/Board_List";
	}

	@RequestMapping("/image")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		return boardService.uploadImg(file);

	}

}
