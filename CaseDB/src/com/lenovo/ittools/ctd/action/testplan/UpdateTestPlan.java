package com.lenovo.ittools.ctd.action.testplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.db2.jcc.a.i;
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

	public String execute() {
			if (conList != null) {
				 //sort conlist by order
				List<SearchCaseBean> tpList = new ArrayList<SearchCaseBean>();
				int tpOrder =1;
				
				while(tpOrder<conList.size()+1){
					for(SearchCaseBean temp: conList){
						if(temp!=null){
							if(tpOrder==temp.getTpOrder()){
								tpList.add(temp);
								tpOrder++;
							}
						}	
					}
				}
				
				
				
				
				
				
				List<TestPlanContent> list = testPlanService
						.findTestPlanContentsByTestPlanInstkey(testPlanInstkey);
				for (TestPlanContent temp : list) {
					testPlanService.deleteTestPlanContent(temp);
				}
				int order = 1;
				int j = 0;
				
				for(SearchCaseBean temp : tpList){
					TestPlanContent tempContent = new TestPlanContent();
					tempContent.setTestPlanContentInstkey(Generator
							.generatorID());
					tempContent.setTestCaseInstkey(temp
							.getCaseInstkey());
					tempContent.setTpOrder(temp.getTpOrder());
					tempContent.setCreateTime(new Date());
					tempContent.setTestPlanInstkey(testPlanInstkey);
					testPlanService.saveTestPlanContent(tempContent);
				}

			}
		return SUCCESS;

	}
}
