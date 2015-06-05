package com.lenovo.ittools.ctd.action.testcase;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;


public class ShowPictureForAjax  extends ActionSupport {

	private String photoPath;
	private TestCaseService tcService;
	
	public TestCaseService getTcService() {
		return tcService;
	}
	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	/**
	 * @author: Kylin Zhang 2012-2-16
	 * @description: 
	 * @param @return
	 * @param @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		
		//HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		
		resp.setContentType("image/jpeg");
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		
		PictureBean pictureBean = tcService.findPictureBeanByPictureInstkey(photoPath);
		//PrintWriter out = resp.getWriter();
		ServletOutputStream out = resp.getOutputStream();
		FileInputStream is = new FileInputStream(new File(pictureBean.getFilePath()));	
		byte[] buffer = new byte[1024];
		int length = 0;
		
		while(-1 != (length = is.read(buffer))){
			out.write(buffer, 0, length);
		}
		
		is.close();
		out.flush();
		out.close();
		
		return null;
	}
}
