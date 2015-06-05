package com.lenovo.ittools.ctd.action.testcase;

import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseHistory;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteTestCase extends ActionSupport {

	private String caseInstkey;
	private TestCaseService tcService;
	
	public String getCaseInstkey() {
		return caseInstkey;
	}

	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}

	public TestCaseService getTcService() {
		return tcService;
	}

	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}

	public String execute(){
		TestCase testCase = tcService.findTestCaseByCaseInstkey(caseInstkey);
		 TestCaseHistory historyTestcase = new TestCaseHistory();
		 historyTestcase.setCaseCode(testCase.getCaseCode());
		 historyTestcase.setCaseInstkey(testCase.getCaseInstkey());
		 historyTestcase.setCaseName(testCase.getCaseName());
		 historyTestcase.setCreator(testCase.getCreator());
		 historyTestcase.setDate(testCase.getDate());
		 historyTestcase.setOwner(testCase.getOwner());
		 historyTestcase.setStatus("Del");
		 historyTestcase.setVersion(testCase.getVersion());
		 tcService.saveTestcaseHistory(historyTestcase);
		 tcService.deleteTestCase(testCase);
		 
		
		return SUCCESS;
	}
}
