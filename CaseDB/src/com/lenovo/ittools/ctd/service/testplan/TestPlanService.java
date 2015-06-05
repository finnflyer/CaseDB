package com.lenovo.ittools.ctd.service.testplan;

import java.util.List;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.common.PageBean;

public interface TestPlanService {
	public PageBean findTestPlanBeansByCondition(String hql,
			int pageSize, int page) throws Exception;
	public List<TestPlanBean> findTestPlanBeansAll();
	public List<TestPlanContent> findTestPlanContentsByTestPlanInstkey(String testPlanInstkey);
	public TestPlanBean findTestPlanBeanByTestPlanInstkey(String testPlanInstkey);
	
	public void saveTestPlan(TestPlanBean testplanBean);
	public void saveTestPlanContent(TestPlanContent testPlanContent);
	
	public void updateTestPlan(TestPlanBean testPlanBean);
	public void deleteTestPlanContent(TestPlanContent testPlanContent);
	
}
