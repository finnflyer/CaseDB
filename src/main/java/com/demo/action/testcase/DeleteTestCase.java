package com.demo.action.testcase;

import com.demo.model.testcase.TestCase;
import com.demo.model.testcase.TestCaseHistroy;
import com.demo.service.testcase.TestCaseService;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Namespace("/phase4")
@Scope("property")
public class DeleteTestCase extends ActionSupport {

	private String caseInstkey;
	@Autowired
	private TestCaseService tcService;
	public String getCaseInstkey() {
		return caseInstkey;
	}

	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}


    @Action(value="DeleteCase",results={@Result(name="success",type="redirect",location="TestCaseHome")})
	public String execute(){
		 TestCase testCase = tcService.findById(caseInstkey);

		 TestCaseHistroy historyTestcase = new TestCaseHistroy();
		 historyTestcase.setCasecode(testCase.getCasecode());
		 historyTestcase.setCaseinstkey(testCase.getCaseinstkey());
		 historyTestcase.setCasename(testCase.getCasename());
		 historyTestcase.setCreator(testCase.getCreator());
		 historyTestcase.setDate(testCase.getDate());
		 historyTestcase.setOwner(testCase.getOwner());
		 historyTestcase.setStatus("Del");
		 historyTestcase.setVersion(testCase.getVersion());
		 tcService.saveTestcaseHistory(historyTestcase);
		testCase.setStatus("Hide");
		tcService.update(testCase);
		 //tcService.delete(testCase);
		return SUCCESS;
	}
}
