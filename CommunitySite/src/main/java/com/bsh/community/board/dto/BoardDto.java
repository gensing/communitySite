package com.bsh.community.board.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class BoardDto {
	
	//save in db variable
	private int seq;
	
	@Size(min=1,max=12)
	private String title;
	
	private String writer;
	
	@NotEmpty
	private String content;
	
	private Date regDate;
	private int cnt;
	private int cate;
	
	private String cateName;
	private String box;
	private String box_name;
	
	//insert, update view 
	private List<MultipartFile> File;
	private List<String> delFile;
	

	//paging variable
	private int page;
	private int nowPage;
	private int startPage;
	private int endPage;
	private int startIDX;
	private int endIDX;
	private String searchCondition;
	private String searchKeyword;
	


	@Override
	public String toString() {
		return "BoardDto [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate=" + regDate + ", cnt=" + cnt + ", File=" + File + ", page=" + page
				+ ", nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", startIDX=" + startIDX + ", endIDX=" + endIDX + ", searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", getNowPage()=" + getNowPage() + ", getStartIDX()=" + getStartIDX() + ", getEndIDX()=" + getEndIDX() + ", getTitle()=" + getTitle()
				+ ", getWriter()=" + getWriter() + ", getContent()=" + getContent() + ", getRegDate()=" + getRegDate() + ", getSeq()=" + getSeq() + ", getCnt()=" + getCnt() + ", getFile()="
				+ getFile() + ", getPage()=" + getPage() + ", getSearchCondition()=" + getSearchCondition() + ", getSearchKeyword()=" + getSearchKeyword() + ", getStartPage()=" + getStartPage()
				+ ", getEndPage()=" + getEndPage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	public String getCateName() {
		return cateName;
	}
	
	

	public String getBox() {
		return box;
	}


	public void setBox(String box) {
		this.box = box;
	}


	public String getBox_name() {
		return box_name;
	}


	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}


	public void setCateName(String cateName) {
		this.cateName = cateName;
	}


	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartIDX() {
		return startIDX;
	}

	public void setStartIDX(int startIDX) {
		this.startIDX = startIDX;
	}

	public int getEndIDX() {
		return endIDX;
	}

	public void setEndIDX(int endIDX) {
		this.endIDX = endIDX;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public List<MultipartFile> getFile() {
		return File;
	}

	public void setFile(List<MultipartFile> file) {
		File = file;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public List<String> getDelFile() {
		return delFile;
	}

	public void setDelFile(List<String> delFile) {
		this.delFile = delFile;
	}

	public int getCate() {
		return cate;
	}

	public void setCate(int cate) {
		this.cate = cate;
	}
	
}
