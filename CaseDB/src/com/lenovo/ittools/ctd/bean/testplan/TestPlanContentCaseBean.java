package com.lenovo.ittools.ctd.bean.testplan;

import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;

public class TestPlanContentCaseBean {
	private String testPlanInstkey;
	private String testPlanContentInstkey;
	private Integer tpOrder;
	private List<SearchCaseBean> list;
	public String getTestPlanInstkey() {
		return testPlanInstkey;
	}
	public void setTestPlanInstkey(String testPlanInstkey) {
		this.testPlanInstkey = testPlanInstkey;
	}
	public String getTestPlanContentInstkey() {
		return testPlanContentInstkey;
	}
	public void setTestPlanContentInstkey(String testPlanContentInstkey) {
		this.testPlanContentInstkey = testPlanContentInstkey;
	}
	public Integer getTpOrder() {
		return tpOrder;
	}
	public void setTpOrder(Integer tpOrder) {
		this.tpOrder = tpOrder;
	}
	public List<SearchCaseBean> getList() {
		return list;
	}
	public void setList(List<SearchCaseBean> list) {
		this.list = list;
	}
	
	
}
