package com.lenovo.ittools.ctd.action.testcase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.aop.ThrowsAdvice;

import com.lenovo.ittools.ctd.bean.testcase.CaseLanMap;
import com.lenovo.ittools.ctd.bean.testcase.LanguagesBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.form.TCFormBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.util.StringFormat;
import com.opensymphony.xwork2.ActionSupport;

public class EditCaseStepOne extends ActionSupport{
	private TCFormBean tcFormbean = TCFormBean.getInstance(); 
	private TestCaseService tcService;
	private String caseInstkey;
	private String caseInfoInstkey;
	private TestCase testCase;
	private TestCaseInfo testCaseInfo;
	

	public String execute() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
		if (userInfo == null) {
			return "logError";
	}
		caseInfoInstkey = (String) session.getAttribute("caseInfoInstkey");
		caseInstkey = (String) session.getAttribute("caseInstkey");
		if(caseInstkey==null){
			throw new Exception();
		}
		testCase = tcService.findTestCaseByCaseInstkey(caseInstkey);
		testCaseInfo = tcService.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
		testCaseInfo.setComments(StringFormat.formatStrForHtml(testCaseInfo.getComments()));
		testCaseInfo.setModifyReason(StringFormat.formatStrForHtml(testCaseInfo.getModifyReason()));
		testCaseInfo.setHardwareInfo(StringFormat.formatStrForHtml(testCaseInfo.getHardwareInfo()));
		testCaseInfo.setLanguageComment(StringFormat.formatStrForHtml(testCaseInfo.getLanguageComment()));

		
		return SUCCESS;
		
	}


	public TCFormBean getTcFormbean() {
		return tcFormbean;
	}


	public void setTcFormbean(TCFormBean tcFormbean) {
		this.tcFormbean = tcFormbean;
	}


	public TestCaseService getTcService() {
		return tcService;
	}


	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}


	public String getCaseInstkey() {
		return caseInstkey;
	}


	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}


	public String getCaseInfoInstkey() {
		return caseInfoInstkey;
	}


	public void setCaseInfoInstkey(String caseInfoInstkey) {
		this.caseInfoInstkey = caseInfoInstkey;
	}


	public TestCase getTestCase() {
		return testCase;
	}


	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}


	public TestCaseInfo getTestCaseInfo() {
		return testCaseInfo;
	}


	public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
		this.testCaseInfo = testCaseInfo;
	}
	
	
	
}
