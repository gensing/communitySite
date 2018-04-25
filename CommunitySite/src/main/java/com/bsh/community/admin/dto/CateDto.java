package com.bsh.community.admin.dto;

public class CateDto {
	int seq;
	String cate_name;
	int box;
	String boxName;
	String oldBoxName;
	
	String err;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public int getBox() {
		return box;
	}
	public void setBox(int box) {
		this.box = box;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public String getOldBoxName() {
		return oldBoxName;
	}
	public void setOldBoxName(String oldBoxName) {
		this.oldBoxName = oldBoxName;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	
	
}
