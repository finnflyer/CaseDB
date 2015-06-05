package com.lenovo.ittools.ctd.bean.testcase;

import java.util.Date;

public class TestCase {

	private String caseInstkey;
	private String caseName;
	private String caseCode;
	private String status;
	private String version;
	private String owner;
	private Date   date;
	private String creator;

	public String getCaseInstkey() {
		return caseInstkey;
	}
	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
}
