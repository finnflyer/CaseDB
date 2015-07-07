package com.lenovo.ittools.ctd.action.common;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.opensymphony.xwork2.ActionSupport;

public class PreUploadFile extends ActionSupport {
       public String instkey;
       public TestToolService testToolService;
       public TestToolBean testToolBean;
       public String execute(){
    	   testToolBean = testToolService.findTestToolBeanByInstkey(instkey);
    	   return SUCCESS;
       }
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
	public TestToolBean getTestToolBean() {
		return testToolBean;
	}
	public void setTestToolBean(TestToolBean testToolBean) {
		this.testToolBean = testToolBean;
	}
       
}
