package com.lenovo.ittools.ctd.action.testcase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.CaseLanguage;
import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseContent;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCaseStepTwo extends ActionSupport {
	private TestCaseInfo testCaseInfo;
	private TestCaseService tcService;
	private List<CaseLanguage> caseLan;
	private String languageComment;
	private String HWinfo;
	private String HWinfoComment;
	private String caseInfoInstkey;
	private String caseInstkey;
	private TestCase testCase;
	private List<TestCaseContent>  testcasecontent;

	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
		caseInfoInstkey = (String) session.getAttribute("caseInfoInstkey");
		caseInstkey = (String) session.getAttribute("caseInstkey");
		if (userInfo == null) {
			return "logError";
		}
		testCase = tcService.findTestCaseByCaseInstkey(caseInstkey);
		testCaseInfo = tcService
				.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
		testCaseInfo.setComments(HWinfoComment);
		testCaseInfo.setHardwareInfo(HWinfo);
		testCaseInfo.setLanguageComment(languageComment);
		tcService.updateTestCaseInfo(testCaseInfo);
		List<CaseLanguage> ctdCaseLan = tcService.findCaseLanguagesByCaseInstkey(caseInstkey);
		for(int i = 0 ;i<ctdCaseLan.size();i++){
			ctdCaseLan.get(i).setLocalSet(caseLan.get(i).getLocalSet());
		}
		tcService.updateCaseLanMap(ctdCaseLan);
		
		testcasecontent = tcService.findTestCaseContentsByCaseInstkey(caseInstkey);
		for(TestCaseContent temp:testcasecontent){
			List<PictureBean> casePics = tcService.findPictureBeansByCaseContentInstkey(temp.getCaseContentInstkey());
			temp.setCasePics(casePics);
		}
		
		
		return SUCCESS;
		
	}

	
	public List<TestCaseContent> getTestcasecontent() {
		return testcasecontent;
	}
	public void setTestcasecontent(List<TestCaseContent> testcasecontent) {
		this.testcasecontent = testcasecontent;
	}


	public TestCaseInfo getTestCaseInfo() {
		return testCaseInfo;
	}

	public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
		this.testCaseInfo = testCaseInfo;
	}

	public TestCaseService getTcService() {
		return tcService;
	}

	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}

	public List<CaseLanguage> getCaseLan() {
		return caseLan;
	}

	public void setCaseLan(List<CaseLanguage> caseLan) {
		this.caseLan = caseLan;
	}

	public String getLanguageComment() {
		return languageComment;
	}

	public void setLanguageComment(String languageComment) {
		this.languageComment = languageComment;
	}

	public String getHWinfo() {
		return HWinfo;
	}

	public void setHWinfo(String hWinfo) {
		HWinfo = hWinfo;
	}

	public String getHWinfoComment() {
		return HWinfoComment;
	}

	public void setHWinfoComment(String hWinfoComment) {
		HWinfoComment = hWinfoComment;
	}

	public String getCaseInfoInstkey() {
		return caseInfoInstkey;
	}

	public void setCaseInfoInstkey(String caseInfoInstkey) {
		this.caseInfoInstkey = caseInfoInstkey;
	}

	public String getCaseInstkey() {
		return caseInstkey;
	}

	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}
	
}
