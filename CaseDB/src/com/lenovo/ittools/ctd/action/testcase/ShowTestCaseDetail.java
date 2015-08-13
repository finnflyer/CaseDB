package com.lenovo.ittools.ctd.action.testcase;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.CaseLanMap;
import com.lenovo.ittools.ctd.bean.testcase.LanguagesBean;
import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseContent;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseFunction;

import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.lenovo.ittools.ctd.util.StringFormat;
import com.opensymphony.xwork2.ActionSupport;

public class ShowTestCaseDetail extends ActionSupport {
		private TestCase testCase;
		private TestCaseInfo testCaseInfo;
	    private List<CaseLanMap>  CaseLan;
	  	private List<LanguagesBean> LanBean;
		private  List<TestCaseContent> testCaseContents;
		private TestCaseService tcService;
		private TestPlanService testPlanService;
		private PictureBean accessoryFile;
		private List<TestPlanBean> testPlanList;
		
		
		public TestPlanService getTestPlanService() {
			return testPlanService;
		}
		public void setTestPlanService(TestPlanService testPlanService) {
			this.testPlanService = testPlanService;
		}
		public List<TestPlanBean> getTestPlanList() {
			return testPlanList;
		}
		public void setTestPlanList(List<TestPlanBean> testPlanList) {
			this.testPlanList = testPlanList;
		}
		public String execute() throws Exception{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();		
			UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
			if (userInfo == null) {
				return "logError";
			}
			testPlanList = testPlanService.findTestPlanBeansAll();
			String caseInstkey = testCase.getCaseInstkey();
			testCase = tcService.findTestCaseByCaseInstkey(caseInstkey);
			testCaseInfo = tcService.findTestCaseInfoByCaseInstkey(caseInstkey);
			testCaseContents = tcService.findTestCaseContentsByCaseInstkey(caseInstkey);
			testCaseInfoCatoSetting(testCaseInfo);
			eachStringforHtml(testCaseInfo,testCaseContents);
			CaseLan=tcService.findCaseLanMapsByCaseInstkey(caseInstkey);
			LanBean=tcService.findLanguagesBeansAll();
			
			int time=0;
			for(TestCaseContent temp : testCaseContents){
				  time = time +temp.getStepTime();
			}
			testCaseInfo.setExecuteTime(time);
			List<PictureBean>  pics=new ArrayList<PictureBean>();
			for(int i=0;i<testCaseContents.size();i++){
				TestCaseContent temp=testCaseContents.get(i);
				pics=tcService.findPictureBeansByCaseContentInstkey(temp.getCaseContentInstkey());
				if(pics.size()>0){
					temp.setCasePics(pics);
				}
			}
			session.setAttribute("caseInstkey", caseInstkey);
			session.setAttribute("caseInfoInstkey", testCaseInfo.getCaseInfoInstkey());
			return SUCCESS;
		}
		public void eachStringforHtml(TestCaseInfo testCaseInfo,List<TestCaseContent> testCaseContents){
			 testCaseInfo.setComments(StringFormat.formatStrForHtml(testCaseInfo.getComments()));
			 testCaseInfo.setModifyReason(StringFormat.formatStrForHtml(testCaseInfo.getModifyReason()));
			 testCaseInfo.setHardwareInfo(StringFormat.formatStrForHtml(testCaseInfo.getHardwareInfo()));
			 for(TestCaseContent temp:testCaseContents){
				 temp.setComments(StringFormat.formatStrForHtml(temp.getComments()));
				 temp.setTestItem(StringFormat.formatStrForHtml(temp.getTestItem()));
				 temp.setTestResult(StringFormat.formatStrForHtml(temp.getTestResult()));
				 temp.setTestStep(StringFormat.formatStrForHtml(temp.getTestStep()));
			 }
		}
		public void testCaseInfoCatoSetting(TestCaseInfo testCaseInfo){
			TestCaseFunction testCaseFunction = tcService.findTestCaseFunctionbyId(testCaseInfo.getFuncId());
			testCaseInfo.setFuncCato(testCaseFunction.getFunctionCato());
			testCaseInfo.setComments(StringFormat.formatStrForHtml(testCaseInfo.getComments()));
			testCaseInfo.setModifyReason(StringFormat.formatStrForHtml(testCaseInfo.getModifyReason()));
			testCaseInfo.setHardwareInfo(StringFormat.formatStrForHtml(testCaseInfo.getHardwareInfo()));
			testCaseInfo.setLanguageComment(StringFormat.formatStrForHtml(testCaseInfo.getLanguageComment()));
            switch(testCaseInfo.getBrandId()){
            case 1:
            	testCaseInfo.setBrandCato("ThinkPad");
            	break;
            case 12:
            	testCaseInfo.setBrandCato("ThinkPad/ThinkStation");
            	break;
            case 2:
            	testCaseInfo.setBrandCato("ThinkStation");
            }
			switch (testCaseInfo.getOsId()) {
			  case 1:
				  		testCaseInfo.setOsCato("xp");
				break;
			  case 12:
				   testCaseInfo.setOsCato("xp/win7");
				   break;
			  case 123:
				   testCaseInfo.setOsCato("xp/win7/win8.x");
				   break;
			  case 1234:
				  testCaseInfo.setOsCato("xp/win7/win8.x/win10");
				  break;
			  case 2:
				  testCaseInfo.setOsCato("win7");
				  break;
			  case 23:
				  testCaseInfo.setOsCato("win7/win8.x");
				  break;
			  case 234:
				  testCaseInfo.setOsCato("win7/win8.x/win10");
				  break;
			  case 3:
				  testCaseInfo.setOsCato("win8.x");
				  break;
			  case 34:
				  testCaseInfo.setOsCato("win8.x/win10");
				  break;
			  case 4:
				  testCaseInfo.setOsCato("win10");
				  break;
			default:
				break;
			}
		}
		public TestCase getTestCase() {
			return testCase;
		}


		public void setTestCase(TestCase testCase) {
			this.testCase = testCase;
		}


		public TestCaseInfo getTestCaseInfo() {
			return testCaseInfo;
		}


		public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
			this.testCaseInfo = testCaseInfo;
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


		public List<TestCaseContent> getTestCaseContents() {
			return testCaseContents;
		}


		public void setTestCaseContents(List<TestCaseContent> testCaseContents) {
			this.testCaseContents = testCaseContents;
		}


		public TestCaseService getTcService() {
			return tcService;
		}


		public void setTcService(TestCaseService tcService) {
			this.tcService = tcService;
		}

		public PictureBean getAccessoryFile() {
			return accessoryFile;
		}


		public void setAccessoryFile(PictureBean accessoryFile) {
			this.accessoryFile = accessoryFile;
		}
		
}
