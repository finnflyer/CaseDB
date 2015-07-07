package com.lenovo.ittools.ctd.action.common;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteToolFile extends ActionSupport {
		public String instkey;
		public TestToolService testToolService;
		public String getInstkey() {
			return instkey;
		}
		public void setInstkey(String instkey) {
			this.instkey = instkey;
		}
		public TestToolService getTestToolService() {
			return testToolService;
		}
		public void setTestToolService(TestToolService testToolService) {
			this.testToolService = testToolService;
		}
		public String execute(){
			TestToolBean  testToolBean = testToolService.findTestToolBeanByInstkey(instkey);
			testToolBean.setStatus("Del");
			testToolService.delete(testToolBean);
			
			
			return SUCCESS;
			
		}
}
