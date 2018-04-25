package com.bsh.community.board.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bsh.community.board.dao.BoardDao;
import com.bsh.community.board.dao.FileDao;
import com.bsh.community.board.dao.ReplyDao;
import com.bsh.community.board.dto.BoardDto;
import com.bsh.community.board.dto.FileDto;
import com.bsh.community.board.dto.ReplyDto;
import com.bsh.community.board.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao BoardDao;

	@Autowired
	private ReplyDao ReplyDao;

	@Autowired
	private FileDao FileDao;

	private String FILE_SAVE_PATH = "d:/upload/";

	@Override
	public Map<String, Object> getBoardList(BoardDto vo) {

		int PAGE_LANG = 10; // 페이징 개수(1~100)
		int VIEW_MAX_CNT = 20; // 게시물 개수(1~100)
		int maxPage;
		int nowPage = vo.getPage();
		int startPage;
		int endPage;
		int startIdx;
		int endIdx;
		double bbsCnt;
		Map<String, Object> m = new HashMap<>();

		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		
		bbsCnt = BoardDao.getBBSCnt(vo);
		
		if(bbsCnt == 0 ){
			maxPage = 1;
		}else{
			maxPage = (int) Math.ceil(bbsCnt / VIEW_MAX_CNT);
		}
		
		if (nowPage <= 0)
			nowPage = 1;
		else if (nowPage > maxPage)
			nowPage = maxPage;

		endPage = (int) Math.ceil((double) nowPage / VIEW_MAX_CNT) * PAGE_LANG;
		startPage = endPage - PAGE_LANG + 1;

		startIdx = (nowPage - 1) * VIEW_MAX_CNT + 1;
		endIdx = nowPage * VIEW_MAX_CNT;

		if (endPage > maxPage)
			endPage = maxPage;

		maxPage = (int) Math.ceil((double) BoardDao.getBBSCnt(vo) / VIEW_MAX_CNT);

		vo.setStartIDX(startIdx);
		vo.setEndIDX(endIdx);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setNowPage(nowPage);
		vo.setCateName(BoardDao.getBoardListTitle(vo));

		m.put("pagingInfo", vo);
		m.put("BoardList", BoardDao.getBoardList(vo));

		return m;

	}

	@Override
	public Map<String, Object> getBoard(BoardDto vo) {
		Map<String, Object> m = new HashMap<>();
		BoardDao.countUp(vo);
		m.put("board", BoardDao.getBoard(vo));
		m.put("reply", ReplyDao.getReplyList(vo));
		m.put("file", FileDao.getFileList(vo));
		return m;
	}

	@Override
	public Map<String, Object> getUpdateBoard(BoardDto vo) {
		Map<String, Object> m = new HashMap<>();
		m.put("board", BoardDao.getBoard(vo));
		m.put("file", FileDao.getFileList(vo));
		return m;
	}

	@Override
	public void insertBoard(BoardDto vo) {
		vo.setWriter(SecurityContextHolder.getContext().getAuthentication().getName()); // 방지
		BoardDao.insertBoard(vo); // selectKey in mybatis 를 통하여 매개변수로 보낸 vo 객체에
		FileDto fileInfo = new FileDto();
		fileInfo.setbId(vo.getSeq());

		for (MultipartFile file : vo.getFile()) {
			if (!file.isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSSSS");
				String fileName = dateFormat.format(Calendar.getInstance().getTime());

				File convFile = new File(FILE_SAVE_PATH + fileName);
				try {
					file.transferTo(convFile);
					fileInfo.setFileName(fileName);
					fileInfo.setFileRealName(file.getOriginalFilename());
					FileDao.insertFile(fileInfo);

				} catch (Exception e) {
					System.out.println("upload err");
				}
			}
		}
	}

	@Override
	public void deleteBoard(BoardDto vo) {
		if(BoardDao.getBoard(vo).getWriter().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
			BoardDao.deleteBoard(vo);
		}
	}

	@Override
	public void updateBoard(BoardDto vo) {
		
		if(BoardDao.getBoard(vo).getWriter().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
			BoardDao.updateBoard(vo);

			FileDto fileInfo = new FileDto();
			fileInfo.setbId(vo.getSeq());

			for (MultipartFile file : vo.getFile()) {
				if (!file.isEmpty()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSSSS");
					String fileName = dateFormat.format(Calendar.getInstance().getTime());

					File convFile = new File(FILE_SAVE_PATH + fileName);
					try {
						file.transferTo(convFile);
						fileInfo.setFileName(fileName);
						fileInfo.setFileRealName(file.getOriginalFilename());
						FileDao.insertFile(fileInfo);
					} catch (Exception e) {
						System.out.println("upload err");
					}
				}
			}

			if (vo.getDelFile() != null) {
				for (String fileName : vo.getDelFile()) {
					fileInfo.setFileRealName(fileName);
					FileDao.delFile(fileInfo);
				}
			}
		}	
	}

	@Override
	public void insertReply(ReplyDto vo) {
		vo.setWriter(SecurityContextHolder.getContext().getAuthentication().getName());
		ReplyDao.insertReply(vo);
	}

	@Override
	public void deleteReply(ReplyDto vo) {
		ReplyDao.deleteReply(vo);
	}

	@Override
	public void downView(HttpServletRequest req, HttpServletResponse resp, String filename) {
		if (FILE_SAVE_PATH.length() == 0)
			FILE_SAVE_PATH = "d:/upload/";

		try {
			req.setCharacterEncoding("UTF-8");
			System.out.println("됩니다:" + req.getParameter("filename"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		OutputStream outputStream = null;
		String fileRealName = FileDao.getFileRealName(filename);

		try {
			File file = new File(FILE_SAVE_PATH + filename);

			String filetype = fileRealName.substring(fileRealName.indexOf(".") + 1, fileRealName.length());
			if (filetype.trim().equalsIgnoreCase("txt")) {
				resp.setContentType("text/plain");
			} else {
				resp.setContentType("application/octet-stream");
			}

			resp.setContentLength((int) file.length());

			if (req.getHeader("User-Agent").indexOf("MSIE") != -1) {
				fileRealName = URLEncoder.encode(fileRealName, "UTF-8").replaceAll("\\+", " ");
				// filename = new String(filename.getBytes("UTF-8"), "8859_1");
			} else {
				fileRealName = new String(fileRealName.getBytes("EUC-KR"), "8859_1");
			}

			resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileRealName + "\"");

			outputStream = resp.getOutputStream();
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(file);
				FileCopyUtils.copy(fis, outputStream);
			} finally {
				if (fis != null)
					fis.close();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				outputStream.close();
				resp.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String uploadImg(MultipartFile file) {
		String fileName="";
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file " + file.getOriginalFilename());
			}
			String path = new ClassPathResource("/").getURI().getPath().replace("WEB-INF/classes/", "resources/img/");
			String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);//확장자 구하기
			fileName = new SimpleDateFormat("yyMMddHHmmssSSSSS").format(Calendar.getInstance().getTime()) +"."+ extension;
			File convFile = new File(path + fileName);
			file.transferTo(convFile);
			System.out.println(new ClassPathResource("/").getURI().getPath());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resources/img/" + fileName;


	}

}
