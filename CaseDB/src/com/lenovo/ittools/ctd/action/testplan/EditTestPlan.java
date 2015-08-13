package com.lenovo.ittools.ctd.action.testplan;

import java.util.ArrayList;
import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.service.testcase.SearchCaseService;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.opensymphony.xwork2.ActionSupport;

public class EditTestPlan extends ActionSupport {
	  private String testPlanInstkey;
	  private List<TestPlanContent> tpContentList;
	  private TestPlanService testPlanService;
	  private SearchCaseService scService;
	  private List<SearchCaseBean> contentList;
	  private String testPlanName;
	  public String execute(){
		  testPlanName =  testPlanService.findTestPlanBeanByTestPlanInstkey(testPlanInstkey).getTestPlanName();  
		  tpContentList = testPlanService.findTestPlanContentsByTestPlanInstkey(testPlanInstkey);
		  contentList = new ArrayList<SearchCaseBean>();
		  int count = 0;
		  for(TestPlanContent temp:tpContentList){
			  System.out.println(temp.getTestCaseInstkey());
			  SearchCaseBean scb = scService.findSearchCaseBeanByCaseInstkey(temp.getTestCaseInstkey());
			  if(scb!=null){
				  int order = temp.getTpOrder();
				  if(count!=0){
					 order = temp.getTpOrder()-count;
				  }
				  scb.setTpOrder(order);
				  SearchBeanCatoSetting(scb);
				  contentList.add(scb);
			  }else{
				  count+=1;
			  }
			  
		  }
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
	public TestPlanService getTestPlanService() {
		return testPlanService;
	}
	public void setTestPlanService(TestPlanService testPlanService) {
		this.testPlanService = testPlanService;
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
	public String getTestPlanName() {
		return testPlanName;
	}
	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}
	  
	
}
