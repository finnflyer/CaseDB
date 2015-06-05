package com.lenovo.ittools.ctd.action.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseContent;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.impl.orb.ParserTable.TestContactInfoListFactory;

public class SaveStepFinal extends ActionSupport{
		private String caseInstkey;
		private String caseInfoInstkey;
		private List<TestCaseContent> items;
		private String pictureInstkey;
		private List<File> upload;
		private List<String> uploadFileName=new ArrayList<String>();
		private List<String> uploadContentType=new ArrayList<String>();
		private String savePath;
		private TestCaseService tcService;
	
		public String execute() throws Exception {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
			caseInfoInstkey = (String) session.getAttribute("caseInfoInstkey");
			caseInstkey = (String) session.getAttribute("caseInstkey");
			if (userInfo == null) {
				return "logError";
		}
			if (null == caseInfoInstkey || "".equals(caseInfoInstkey)) {
				throw new Exception();
			}
			if (null == caseInstkey || "".equals(caseInstkey)) {
				throw new Exception();
			}
			savePath = "D:\\CTDDataBase\\CasePics\\"+caseInstkey;
			File saveFile = new File(savePath);
			if(!saveFile.exists()){
				 saveFile.mkdir();
			}
			List<PictureBean> pictureBeanList = new ArrayList<PictureBean>();
			if(upload != null){
				for (int i=0;i<upload.size();i++){
					String[] strs=uploadFileName.get(i).split("\\.");
					String surfix=strs[strs.length-1];
					String filename=strs[strs.length-2];
					java.io.FileOutputStream fos=new FileOutputStream(savePath+"\\"+filename+'.'+surfix); 
					FileInputStream fis=new FileInputStream(upload.get(i));
					byte[] buffer=new byte[1024];
					int len=0;
					while((len=fis.read(buffer))>0){
						fos.write(buffer,0,len);
					}
					PictureBean cp=new PictureBean();
					cp.setType(0);
					cp.setFilePath(savePath+"\\"+filename+'.'+surfix);
					cp.setFileName(uploadFileName.get(i));	
					cp.setCreateDate(new Date());
				    pictureBeanList.add(cp);
				}			
			}
		
			int order=0;
			int totalTime=0;
			for(TestCaseContent temp:items){
				if(temp!=null){
					temp.setCaseInstkey(caseInstkey);
					String testCaseContentInstkey = Generator.generatorID();
					temp.setCaseContentInstkey(testCaseContentInstkey);
					temp.setOrderId(order);
					totalTime = totalTime+temp.getStepTime();
					tcService.saveTestCaseContent(temp);
					//save Each content picture.
					if(temp.getHasPic().get(0)!= -1){
						for(Integer hasPic:temp.getHasPic()){
							 pictureBeanList.get(0).setCaseContentInstkey(testCaseContentInstkey);
							 pictureBeanList.get(0).setCaseInstkey(caseInstkey);
							 pictureBeanList.get(0).setPictureInstkey(Generator.generatorID());
							 tcService.savePictureBean(pictureBeanList.get(0));
							 pictureBeanList.remove(0); 
						}
					}
					order++;
				}
			}
			TestCaseInfo testCaseInfo = tcService.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
			testCaseInfo.setExecuteTime(totalTime);
			tcService.updateTestCaseInfo(testCaseInfo);
			
		    session.setAttribute("caseInstkey", "");
		    session.setAttribute("caseInfoInstkey", "");
			return SUCCESS;
		}
		public String getCaseInstkey() {
			return caseInstkey;
		}
		public void setCaseInstkey(String caseInstkey) {
			this.caseInstkey = caseInstkey;
		}
		public String getCaseInfoInstkey() {
			return caseInfoInstkey;
		}

		public void setCaseInfoInstkey(String caseInfoInstkey) {
			this.caseInfoInstkey = caseInfoInstkey;
		}

		public List<TestCaseContent> getItems() {
			return items;
		}

		public void setItems(List<TestCaseContent> items) {
			this.items = items;
		}

		public String getPictureInstkey() {
			return pictureInstkey;
		}

		public void setPictureInstkey(String pictureInstkey) {
			this.pictureInstkey = pictureInstkey;
		}

		public List<File> getUpload() {
			return upload;
		}

		public void setUpload(List<File> upload) {
			this.upload = upload;
		}

		public String getSavePath() {
			return savePath;
		}

		public void setSavePath(String savePath) {
			this.savePath = savePath;
		}

		public TestCaseService getTcService() {
			return tcService;
		}

		public void setTcService(TestCaseService tcService) {
			this.tcService = tcService;
		}

		public List<String> getUploadFileName() {
			return uploadFileName;
		}

		public void setUploadFileName(List<String> uploadFileName) {
			this.uploadFileName = uploadFileName;
		}

		public List<String> getUploadContentType() {
			return uploadContentType;
		}

		public void setUploadContentType(List<String> uploadContentType) {
			this.uploadContentType = uploadContentType;
		}
		
		
		
}
