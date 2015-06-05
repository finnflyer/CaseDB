package com.lenovo.ittools.ctd.action.testplan;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteTestPlan extends ActionSupport {
		private String testPlanInstkey;
		private TestPlanService testPlanService;
		public String execute() throws Exception{
			TestPlanBean testPlanBean = testPlanService.findTestPlanBeanByTestPlanInstkey(testPlanInstkey);
			testPlanBean.setTestPlanStatus("Del");
			testPlanService.updateTestPlan(testPlanBean);
			return SUCCESS;
		}
		public String getTestPlanInstkey() {
			return testPlanInstkey;
		}
		public void setTestPlanInstkey(String testPlanInstkey) {
			this.testPlanInstkey = testPlanInstkey;
		}
		public TestPlanService getTestPlanService() {
			return testPlanService;
		}
		public void setTestPlanService(TestPlanService testPlanService) {
			this.testPlanService = testPlanService;
		}
		
}
