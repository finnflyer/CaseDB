package com.lenovo.ittools.ctd.bean.testcase;

import java.util.Date;

public class PictureBean {
	private String pictureInstkey;
	private String filePath;
	private String fileName;
	private String caseInstkey;
	private String caseContentInstkey;
	private Integer type;
	private Date createDate;
	public String getPictureInstkey() {
		return pictureInstkey;
	}
	public void setPictureInstkey(String pictureInstkey) {
		this.pictureInstkey = pictureInstkey;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCaseInstkey() {
		return caseInstkey;
	}
	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}
	public String getCaseContentInstkey() {
		return caseContentInstkey;
	}
	public void setCaseContentInstkey(String caseContentInstkey) {
		this.caseContentInstkey = caseContentInstkey;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	

}
