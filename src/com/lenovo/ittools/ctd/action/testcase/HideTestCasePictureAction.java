package com.lenovo.ittools.ctd.action.testcase;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;

public class HideTestCasePictureAction extends ActionSupport {
	private String pictureInstkey;
	private TestCaseService tcService;
	public String execute() throws Exception{
		System.out.println("======================Start to del photo=========");
		System.out.println(pictureInstkey);
		PictureBean pictureBean = tcService.findPictureBeanByPictureInstkey(pictureInstkey);
		pictureBean.setCaseContentInstkey("");
		pictureBean.setCaseInstkey("");
		tcService.updatePictureBean(pictureBean);
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html; charset=utf-8");
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		PrintWriter out = resp.getWriter();
		out.println("success");
		out.flush();
		out.close();			
		return null;
	}
	public String getPictureInstkey() {
		return pictureInstkey;
	}
	public void setPictureInstkey(String pictureInstkey) {
		this.pictureInstkey = pictureInstkey;
	}
	public TestCaseService getTcService() {
		return tcService;
	}
	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}
	
	
}
