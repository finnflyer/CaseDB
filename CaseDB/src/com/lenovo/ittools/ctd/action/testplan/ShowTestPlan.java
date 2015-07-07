package com.lenovo.ittools.ctd.action.testplan;

import java.util.ArrayList;
import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContentCaseBean;
import com.lenovo.ittools.ctd.service.testcase.SearchCaseService;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowTestPlan extends ActionSupport{
	  private String testPlanInstkey;
	  private List<TestPlanContent> tpContentList;
	  private TestPlanService testPlanService;
	  private SearchCaseService scService;
	  private TestCaseService tcService;
	  private List<SearchCaseBean> contentList;
	  private String totalTime;
	  private String testPlanName;
	  private TestPlanBean testPlan;
	  
	  
	  public String execute(){
		  tpContentList = testPlanService.findTestPlanContentsByTestPlanInstkey(testPlanInstkey);
		  testPlan = testPlanService.findTestPlanBeanByTestPlanInstkey(testPlanInstkey);
		  testPlanName = testPlanService.findTestPlanBeanByTestPlanInstkey(testPlanInstkey).getTestPlanName();
		  
		  contentList = new ArrayList<SearchCaseBean>();
		  int total=0;
		  for(TestPlanContent temp:tpContentList){
			  SearchCaseBean scb = scService.findSearchCaseBeanByCaseInstkey(temp.getTestCaseInstkey());
			  if(scb!=null){
				  TestCaseInfo testcaseInfo = tcService.findTestCaseInfoByCaseInstkey(temp.getTestCaseInstkey());
				  total = total + testcaseInfo.getExecuteTime();
				  scb.setTpOrder(temp.getTpOrder());
				  SearchBeanCatoSetting(scb);
				  contentList.add(scb);
			  }
		  }
		  
		  totalTime = String.valueOf(total);
		  return SUCCESS;
	  }
	  public void SearchBeanCatoSetting(SearchCaseBean temp){
			switch (temp.getBrandId()){
			 case 1:
				 temp.setBrandCato("ThinkPad");
	         	break;
	         case 12:
	        	 temp.setBrandCato("ThinkPad/ThinkStation");
	         	break;
	         case 2:
	        	 temp.setBrandCato("ThinkStation");
	        	 break;
	         }
			switch (temp.getOsId()) {
			  case 1:
				  temp.setOsCato("xp");
				break;
			  case 12:
				  temp.setOsCato("xp/win7");
				   break;
			  case 123:
				  temp.setOsCato("xp/win7/win8.x");
				   break;
			  case 1234:
				  temp.setOsCato("xp/win7/win8.x/win10");
				  break;
			  case 2:
				  temp.setOsCato("win7");
				  break;
			  case 23:
				  temp.setOsCato("win7/win8.x");
				  break;
			  case 234:
				  temp.setOsCato("win7/win8.x/win10");
				  break;
			  case 3:
				  temp.setOsCato("win8.x");
				  break;
			  case 34:
				  temp.setOsCato("win8.x/win10");
				  break;
			  case 4:
				  temp.setOsCato("win10");
				  break;
			default:
				break;
			}
		}
	  
	  
	public TestPlanBean getTestPlan() {
		return testPlan;
	}
	public void setTestPlan(TestPlanBean testPlan) {
		this.testPlan = testPlan;
	}
	public String getTestPlanName() {
		return testPlanName;
	}
	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}
	public TestCaseService getTcService() {
		return tcService;
	}
	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getTestPlanInstkey() {
		return testPlanInstkey;
	}

	public void setTestPlanInstkey(String testPlanInstkey) {
		this.testPlanInstkey = testPlanInstkey;
	}

	

	public List<TestPlanContent> getTpContentList() {
		return tpContentList;
	}

	public void setTpContentList(List<TestPlanContent> tpContentList) {
		this.tpContentList = tpContentList;
	}


	public SearchCaseService getScService() {
		return scService;
	}

	public void setScService(SearchCaseService scService) {
		this.scService = scService;
	}

	public List<SearchCaseBean> getContentList() {
		return contentList;
	}

	public void setContentList(List<SearchCaseBean> contentList) {
		this.contentList = contentList;
	}

	public TestPlanService getTestPlanService() {
		return testPlanService;
	}

	public void setTestPlanService(TestPlanService testPlanService) {
		this.testPlanService = testPlanService;
	}

		
}
