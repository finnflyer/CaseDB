package com.lenovo.ittools.ctd.action.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ibm.db2.jcc.a.r;
import com.lenovo.ittools.ctd.bean.testcase.CaseLanMap;
import com.lenovo.ittools.ctd.bean.testcase.CaseLanguage;
import com.lenovo.ittools.ctd.bean.testcase.LanguagesBean;
import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseContent;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class SaveStepTwo extends ActionSupport {
	private String languageComment;
	private String HWinfo;
	private String HWinfoComment;
	private List<CaseLanguage> lanMap;
	private TestCaseService tcService;
	private String caseInfoInstkey;
	private String caseInstkey;
	private List<CaseLanMap> CaseLan;
	private List<LanguagesBean> LanBean;
	private String caseName;
	private List<File> accessory = new ArrayList<File>();
	private List<String> accessoryFileName = new ArrayList<String>();
	private List<String> accessoryContentType = new ArrayList<String>();

	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
		caseInfoInstkey = (String) session.getAttribute("caseInfoInstkey");
		caseInstkey = (String) session.getAttribute("caseInstkey");
		if (userInfo == null) {
				return "logError";
		}
		if (null == caseInfoInstkey || "".equals(caseInfoInstkey)) {
			return ERROR;
		}
		if (null == caseInstkey || "".equals(caseInstkey)) {
			return ERROR;
		}
		String savePath = "D:\\CTDDataBase\\Attachment";
		TestCaseInfo testCaseInfo = tcService
				.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
		testCaseInfo.setComments(HWinfoComment);
		testCaseInfo.setHardwareInfo(HWinfo);
		testCaseInfo.setLanguageComment(languageComment);
		tcService.updateTestCaseInfo(testCaseInfo);
		List<CaseLanguage> ctdCaseLan = new ArrayList<CaseLanguage>();
		for(int i=2;i<32;i++){
			lanMap.get(i).setCaseInstkey(caseInstkey);
			ctdCaseLan.add(lanMap.get(i));
		}
		tcService.saveCaseLanMap(ctdCaseLan);
		if(accessory!=null){
			for (int i = 0; i < accessory.size(); i++) {
				String[] strs = accessoryFileName.get(i).split("\\.");
				String surfix = strs[strs.length - 1];
				String filename = strs[strs.length - 2];
				savePath = savePath + "\\" + caseInstkey;
				File saveFile = new File(savePath);
				if (!saveFile.exists()) {
					saveFile.mkdir();
				}
				java.io.FileOutputStream fos = new FileOutputStream(savePath + "\\"
						+ filename + '.' + surfix);
				FileInputStream fis = new FileInputStream(accessory.get(i));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				PictureBean pictureBean = new PictureBean();
				pictureBean.setType(1);
				pictureBean.setCaseInstkey(caseInstkey);
				pictureBean.setCaseContentInstkey("");
				pictureBean.setFilePath(filename + '.' + surfix);
				pictureBean.setFileName(accessoryFileName.get(i));
				pictureBean.setPictureInstkey(Generator.generatorID());
				pictureBean.setCreateDate(new Date());
				tcService.savePictureBean(pictureBean);	
			}
		}
		
		return SUCCESS;
	}

	@Override
	public void validate() {
		try {
			LanBean = tcService.findLanguagesBeansAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == languageComment || "".equals(languageComment)) {
			this.addFieldError("languageComment",
					" The languageComment field is required and should not be null");
		}
		if (null == HWinfo || "".equals(HWinfo)) {
			this.addFieldError("HWinfo",
					" The HWinfo field is required and should not be null");
		}
		if (null == HWinfoComment || "".equals(HWinfoComment)) {
			this.addFieldError("HWinfoComment",
					" The HWinfoComment field is required and should not be null");
		}
	}

	public String getCaseInstkey() {
		return caseInstkey;
	}

	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}

	public String getLanguageComment() {
		return languageComment;
	}

	public void setLanguageComment(String languageComment) {
		this.languageComment = languageComment;
	}

	public String getHWinfo() {
		return HWinfo;
	}

	public void setHWinfo(String hWinfo) {
		HWinfo = hWinfo;
	}

	public String getHWinfoComment() {
		return HWinfoComment;
	}

	public void setHWinfoComment(String hWinfoComment) {
		HWinfoComment = hWinfoComment;
	}

	

	public List<CaseLanguage> getLanMap() {
		return lanMap;
	}

	public void setLanMap(List<CaseLanguage> lanMap) {
		this.lanMap = lanMap;
	}

	public TestCaseService getTcService() {
		return tcService;
	}

	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}

	public String getCaseInfoInstkey() {
		return caseInfoInstkey;
	}

	public void setCaseInfoInstkey(String caseInfoInstkey) {
		this.caseInfoInstkey = caseInfoInstkey;
	}

	public List<CaseLanMap> getCaseLan() {
		return CaseLan;
	}

	public void setCaseLan(List<CaseLanMap> caseLan) {
		CaseLan = caseLan;
	}

	public List<LanguagesBean> getLanBean() {
		return LanBean;
	}

	public void setLanBean(List<LanguagesBean> lanBean) {
		LanBean = lanBean;
	}

	public List<File> getAccessory() {
		return accessory;
	}

	public void setAccessory(List<File> accessory) {
		this.accessory = accessory;
	}

	public List<String> getAccessoryFileName() {
		return accessoryFileName;
	}

	public void setAccessoryFileName(List<String> accessoryFileName) {
		this.accessoryFileName = accessoryFileName;
	}

	public List<String> getAccessoryContentType() {
		return accessoryContentType;
	}

	public void setAccessoryContentType(List<String> accessoryContentType) {
		this.accessoryContentType = accessoryContentType;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

}
