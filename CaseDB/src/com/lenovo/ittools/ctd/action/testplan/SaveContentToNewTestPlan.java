package com.lenovo.ittools.ctd.action.testplan;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.lenovo.ittools.ctd.util.DataTimeString;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class SaveContentToNewTestPlan extends ActionSupport {
	
		private String str;
		private String testPlanName;
		private String testPlanDescription;
		private String testPlanComments;
		private String projectName;
		private TestPlanService testPlanService;
		
		public String execute() throws Exception{
			HttpServletRequest req = ServletActionContext.getRequest();
			UserInfoBean userInfo = (UserInfoBean)req.getSession().getAttribute("userInfo");
			TestPlanBean testPlanBean = new TestPlanBean();
		    String testPlanInstkey = Generator.generatorID();
		    String projectInstkey = Generator.generatorID();
		    testPlanBean.setTestPlanInstkey(testPlanInstkey);
		    testPlanBean.setTestPlanComments(testPlanComments);
		    testPlanBean.setTestPlanDescription(testPlanDescription);
		    testPlanBean.setCreateDate(new Date());
		    testPlanBean.setProjectName(projectName);
		    testPlanBean.setTestPlanOwnerInstkey(userInfo.getUserID());
		    testPlanBean.setTestPlanOwner(userInfo.getUserName());
		    testPlanBean.setTestPlanStatus("Active");
		    testPlanBean.setTestPlanType("Template");
		    testPlanBean.setTestPlanName(testPlanName);
		    testPlanBean.setProjectInstkey(projectInstkey);
		    testPlanService.saveTestPlan(testPlanBean);
		    
		    //add content to testplan
			String[] ary = str.split(" ");
			int i = 1;
			TestPlanContent tpContent = new TestPlanContent();
			for (int list = 1; list < ary.length; list++) {
				tpContent.setCreateTime(new Date());
				tpContent.setTestCaseInstkey(ary[list]);
				tpContent.setTestPlanContentInstkey(Generator.generatorID());
				tpContent.setTestPlanInstkey(testPlanInstkey);
				tpContent.setTpOrder(i);
				testPlanService.saveTestPlanContent(tpContent);
				i++;
			}
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setHeader("pragma", "no-cache");
			resp.setHeader("cache-control", "no-cache");
			PrintWriter out = resp.getWriter();
			out.print("success");
			out.flush();
			out.close();
			return null;
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public String getTestPlanName() {
			return testPlanName;
		}

		public void setTestPlanName(String testPlanName) {
			this.testPlanName = testPlanName;
		}

		public String getTestPlanDescription() {
			return testPlanDescription;
		}

		public void setTestPlanDescription(String testPlanDescription) {
			this.testPlanDescription = testPlanDescription;
		}

		public String getTestPlanComments() {
			return testPlanComments;
		}

		public void setTestPlanComments(String testPlanComments) {
			this.testPlanComments = testPlanComments;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public TestPlanService getTestPlanService() {
			return testPlanService;
		}

		public void setTestPlanService(TestPlanService testPlanService) {
			this.testPlanService = testPlanService;
		}

}
