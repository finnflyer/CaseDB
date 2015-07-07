package com.lenovo.ittools.ctd.action.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.lenovo.ittools.ctd.util.Generator;
import com.lenovo.ittools.ctd.util.StringFormat;
import com.opensymphony.xwork2.ActionSupport;

public class PreUploadFileAction extends ActionSupport {
		public String  toolName;
		public String  description;
		public TestToolService testToolService;
		public TestToolBean testToolBean;
		
		public String execute(){
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
			String instkey = Generator.generatorID();
			testToolBean = new TestToolBean();
			testToolBean.setInstkey(instkey);
			testToolBean.setDescription(description);
			testToolBean.setUploadTime(new Date());
			testToolBean.setStatus("New");
			testToolBean.setOwner(userInfo.getUserName());
			testToolBean.setToolName(toolName);
			testToolService.save(testToolBean);
			return SUCCESS;
		}

		public TestToolBean getTestToolBean() {
			return testToolBean;
		}

		public void setTestToolBean(TestToolBean testToolBean) {
			this.testToolBean = testToolBean;
		}

		public String getToolName() {
			return toolName;
		}

		public void setToolName(String toolName) {
			this.toolName = toolName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public TestToolService getTestToolService() {
			return testToolService;
		}

		public void setTestToolService(TestToolService testToolService) {
			this.testToolService = testToolService;
		}
		
		
}
