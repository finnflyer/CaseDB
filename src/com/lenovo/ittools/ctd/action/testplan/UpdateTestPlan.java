package com.lenovo.ittools.ctd.action.testplan;

import java.util.Date;
import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateTestPlan extends ActionSupport {
	  private String testPlanInstkey;
	  private TestPlanService testPlanService;
	  private List<SearchCaseBean> conList;
	  
	  public String getTestPlanInstkey() {
		return testPlanInstkey;
	}

	public void setTestPlanInstkey(String testPlanInstkey) {
		this.testPlanInstkey = testPlanInstkey;
	}

	public TestPlanService getTestPlanService() {
		return testPlanService;
	}

	public void setTestPlanService(TestPlanService testPlanService) {
		this.testPlanService = testPlanService;
	}

	public List<SearchCaseBean> getConList() {
		return conList;
	}

	public void setConList(List<SearchCaseBean> conList) {
		this.conList = conList;
	}

	public String execute(){
		  if(conList!=null){
			  List<TestPlanContent> list = testPlanService.findTestPlanContentsByTestPlanInstkey(testPlanInstkey);
			  for(TestPlanContent temp:list){
				  testPlanService.deleteTestPlanContent(temp);
			  }
				int order = 1;
				int j = 0;
				System.out.println(conList.size());
					for (int i = 0; i < conList.size(); i++) {
						if(conList.get(i)!=null){
							if(conList.get(i).getTpOrder()==order){
								TestPlanContent tempContent = new TestPlanContent();
								tempContent.setTestPlanContentInstkey(Generator.generatorID());
								tempContent.setTestCaseInstkey(conList.get(i).getCaseInstkey());
								tempContent.setTpOrder(order);
								tempContent.setCreateTime(new Date());
								tempContent.setTestPlanInstkey(testPlanInstkey);
								testPlanService.saveTestPlanContent(tempContent);
								order++;
								j++;
							}
						}
					}
		  }
		  return SUCCESS;
		  
	  }
}
