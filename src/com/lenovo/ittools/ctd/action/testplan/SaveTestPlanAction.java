package com.lenovo.ittools.ctd.action.testplan;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class SaveTestPlanAction extends ActionSupport {
	private TestPlanService testPlanService;
	
	public TestPlanService getTestPlanService() {
		return testPlanService;
	}

	public void setTestPlanService(TestPlanService testPlanService) {
		this.testPlanService = testPlanService;
	}

	public String execute() {
		TestPlanBean testPlanBean = new TestPlanBean();
		  String testPlanInstkey = Generator.generatorID();
			TestPlanContent tpContent = new TestPlanContent();
			tpContent.setCreateTime(new Date());
			tpContent.setTestCaseInstkey(testPlanInstkey);
			tpContent.setTestPlanContentInstkey(testPlanInstkey);
			tpContent.setTestPlanInstkey(testPlanInstkey);
			tpContent.setTpOrder(1);
		    testPlanService.saveTestPlanContent(tpContent);
		return null;
	}
	
}
