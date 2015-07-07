package com.lenovo.ittools.ctd.action.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction  extends ActionSupport{

	public TestToolBean toolBean;
	public String instkey;
	public TestToolService testToolService;
	public InputStream getInputStream() throws Exception{
		toolBean = testToolService.findTestToolBeanByInstkey(instkey);
		String path = toolBean.getPath();
		InputStream is = new FileInputStream(new File(path));
		
		return is;
	}
	public String execute() throws Exception {
		//System.out.println("downlaod action execute...");
		return super.execute();
	}
	public TestToolBean getToolBean() {
		return toolBean;
	}
	public void setToolBean(TestToolBean toolBean) {
		this.toolBean = toolBean;
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
	
}
