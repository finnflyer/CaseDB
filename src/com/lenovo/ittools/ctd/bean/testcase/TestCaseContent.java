package com.lenovo.ittools.ctd.bean.testcase;

import java.util.ArrayList;
import java.util.List;

public class TestCaseContent {
	
	 private String caseContentInstkey;
	 private String caseInstkey;
	 private Integer orderId;
	 private String caseLevel;
	 private String testItem;
	 private String testResult;
	 private String testStep;
	 private String comments;
	 private Integer stepTime;
	private List<Integer> hasPic=new ArrayList<Integer>();
	private String picName="";
	private List<PictureBean>   CasePics;


	public List<Integer> getHasPic() {
			return hasPic;
		}
		public void setHasPic(List<Integer> hasPic) {
			this.hasPic = hasPic;
		}
		public String getPicName() {
			return picName;
		}
		public void setPicName(String picName) {
			this.picName = picName;
		}
	public String getCaseContentInstkey() {
		return caseContentInstkey;
	}
	public void setCaseContentInstkey(String caseContentInstkey) {
		this.caseContentInstkey = caseContentInstkey;
	}
	public String getCaseInstkey() {
		return caseInstkey;
	}
	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}


	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCaseLevel() {
		return caseLevel;
	}
	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}
	public String getTestItem() {
		return testItem;
	}
	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getTestStep() {
		return testStep;
	}
	public void setTestStep(String testStep) {
		this.testStep = testStep;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getStepTime() {
		return stepTime;
	}
	public void setStepTime(Integer stepTime) {
		this.stepTime = stepTime;
	}
	public List<PictureBean> getCasePics() {
		return CasePics;
	}
	public void setCasePics(List<PictureBean> casePics) {
		CasePics = casePics;
	}
	 
}
