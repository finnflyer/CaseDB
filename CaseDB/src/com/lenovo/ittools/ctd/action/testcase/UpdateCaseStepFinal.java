package com.lenovo.ittools.ctd.action.testcase;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.OldPicRelation;
import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseContent;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCaseStepFinal extends ActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UpdateCaseStepFinal.class);

	private String caseInstkey;
	private String caseInfoInstkey;
	private TestCaseService tcService;
	private List<TestCaseContent> tcContent;
	private List<PictureBean> picturebean;
	private List<File> upload;
	private List<String> uploadFileName = new ArrayList<String>();
	private List<String> uploadContentType = new ArrayList<String>();
	private List<OldPicRelation> olderPicRelations = new ArrayList<OldPicRelation>();
	private String savePath;

	public String execute() throws Exception {
		logger.info("Test case content item "+tcContent.size());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
		caseInfoInstkey = (String) session.getAttribute("caseInfoInstkey");
		caseInstkey = (String) session.getAttribute("caseInstkey");
		if (userInfo == null) {
			return "logError";
	}
		savePath = "D:\\CTDDataBase\\CasePics\\" + caseInstkey;
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdir();
		}
		List<PictureBean> pictureBeanList = new ArrayList<PictureBean>();
		if (upload != null) {
			for (int i = 0; i < upload.size(); i++) {
				String[] strs = uploadFileName.get(i).split("\\.");
				String surfix = strs[strs.length - 1];
				String filename = strs[strs.length - 2];
				java.io.FileOutputStream fos = new FileOutputStream(savePath
						+ "\\" + filename + '.' + surfix);
				FileInputStream fis = new FileInputStream(upload.get(i));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				PictureBean cp = new PictureBean();
				cp.setType(0);
				cp.setFilePath(savePath + "\\" + filename + '.' + surfix);
				cp.setFileName(uploadFileName.get(i));
				cp.setCreateDate(new Date());
				pictureBeanList.add(cp);
			}
		}
		
		/**
		 * @author Chill Huang 2012-6-23
		 * @description update test case content by two ways a, if new test
		 *              content items = older one, not create the new records,
		 *              b, if new test content items != older one, then update
		 *              older records ,not link to case id create the new
		 *              records in DB. save the new data.
		 */
		List<PictureBean> olderPictureBeans = new ArrayList<PictureBean>();
		List<TestCaseContent> olderTestCaseContent = tcService
				.findTestCaseContentsByCaseInstkey(caseInstkey);
		for (TestCaseContent temp : olderTestCaseContent) {
			List<PictureBean> pictureBeans = tcService
					.findPictureBeansByCaseContentInstkey(temp
							.getCaseContentInstkey());
			for (PictureBean cp : pictureBeans) {
				olderPictureBeans.add(cp);
			}
		}
		TestCase testCase = tcService.findTestCaseByCaseInstkey(caseInstkey);
		for (TestCaseContent temp : olderTestCaseContent){
			temp.setCaseInstkey(testCase.getCaseCode());
			tcService.updateTestContent(temp);
		}
		int order = 0;
		int totalTime =0;
		for (TestCaseContent temp : tcContent) {
			if (temp != null) {
				temp.setCaseInstkey(caseInstkey);
				String testCaseContentInstkey = Generator.generatorID();
				temp.setCaseContentInstkey(testCaseContentInstkey);
				temp.setOrderId(order);
			
				tcService.saveTestCaseContent(temp);
				totalTime = totalTime + temp.getStepTime();
				// save Older content picture
				for (OldPicRelation relation : olderPicRelations) {
					if (relation.getOldpic_order() == order) {
						for (PictureBean cp : olderPictureBeans) {
							if (cp.getFilePath().equalsIgnoreCase(
									relation.getOldpic_path())) {
								cp.setCaseContentInstkey(testCaseContentInstkey);
								cp.setCaseInstkey(caseInstkey);
								tcService.savePictureBean(cp);
							}
						}
					}
				}
				// save Each content picture.
				if (temp.getHasPic().get(0) != -1) {
					for (Integer hasPic : temp.getHasPic()) {
						pictureBeanList.get(0).setCaseContentInstkey(
								testCaseContentInstkey);
						pictureBeanList.get(0).setCaseInstkey(caseInstkey);
						pictureBeanList.get(0).setPictureInstkey(
								Generator.generatorID());
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

	public TestCaseService getTcService() {
		return tcService;
	}

	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}

	public List<TestCaseContent> getTcContent() {
		return tcContent;
	}

	public void setTcContent(List<TestCaseContent> tcContent) {
		this.tcContent = tcContent;
	}

	public List<PictureBean> getPicturebean() {
		return picturebean;
	}

	public void setPicturebean(List<PictureBean> picturebean) {
		this.picturebean = picturebean;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
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

	public List<OldPicRelation> getOlderPicRelations() {
		return olderPicRelations;
	}

	public void setOlderPicRelations(List<OldPicRelation> olderPicRelations) {
		this.olderPicRelations = olderPicRelations;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
}
