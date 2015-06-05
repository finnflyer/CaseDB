package com.lenovo.ittools.ctd.action.testplan;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class SaveContentToOlderTestPlan  extends ActionSupport{
		private String str;
		private String testPlanInstkey;
		private TestPlanService testPlanService;
		public String getStr() {
			return str;
		}
		public void setStr(String str) {
			this.str = str;
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
		public String execute() throws Exception{
			HttpServletRequest req = ServletActionContext.getRequest();
			UserInfoBean userInfo = (UserInfoBean)req.getSession().getAttribute("userInfo");
			
			List<TestPlanContent> tpc = testPlanService.findTestPlanContentsByTestPlanInstkey(testPlanInstkey);
			String[] ary = str.split(" ");
			int i = 1;
			TestPlanContent tpContent = new TestPlanContent();
			for (int list = 1; list < ary.length; list++) {
				tpContent.setCreateTime(new Date());
				tpContent.setTestCaseInstkey(ary[list]);
				tpContent.setTestPlanContentInstkey(Generator.generatorID());
				tpContent.setTestPlanInstkey(testPlanInstkey);
				tpContent.setTpOrder(i+tpc.size());
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
}
