package com.lenovo.ittools.ctd.action.common;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFileToServer extends ActionSupport {
	private File fileInput;   
	private String fileInputFileName;
	private String savePath;
	private String instkey;
	private TestToolService testToolService;
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		savePath = "D:\\CTDDataBase\\Attachment\\"+instkey;
	    File fileDir = new File(savePath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		fileInput.renameTo(new File(savePath.toString()+"\\"+ fileInputFileName));
		TestToolBean testToolBean = testToolService.findTestToolBeanByInstkey(instkey);
		testToolBean.setUploadFileName(fileInputFileName);
		testToolBean.setPath(savePath.toString()+"\\"+ fileInputFileName);
		testToolService.update(testToolBean);
		response.getWriter().print("File upload successful:" + fileInputFileName + "");

		return null;
	}
	public File getFileInput() {
		return fileInput;
	}
	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}
	public String getFileInputFileName() {
		return fileInputFileName;
	}
	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
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
