package com.lenovo.ittools.ctd.action.common;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowToolDetail extends ActionSupport{
	public TestToolBean  testToolBean;
	public TestToolService testToolService;
	public String execute(){
		testToolBean = testToolService.findTestToolBeanByInstkey(testToolBean.getInstkey());
		return SUCCESS;
	}
	public TestToolBean getTestToolBean() {
		return testToolBean;
	}
	public void setTestToolBean(TestToolBean testToolBean) {
		this.testToolBean = testToolBean;
	}
	public TestToolService getTestToolService() {
		return testToolService;
	}
	public void setTestToolService(TestToolService testToolService) {
		this.testToolService = testToolService;
	}
	
}
