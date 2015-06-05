package com.lenovo.ittools.ctd.dao.testplan;

import java.util.List;

import sun.reflect.generics.tree.VoidDescriptor;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;

public interface TestPlanDao {
		public List<TestPlanBean> findByConditionForPage(final String hql, final int offset, final int length) throws Exception;
		public int getAllRowCount(String hql) throws Exception;
		
		public List<TestPlanBean> findTestPlanBeansAll();
		public List<TestPlanContent> findTestPlanContentsByTestPlanInstkey(String testPlanInstkey);
		public TestPlanBean findTestPlanBeanByTestPlanInstkey(String testPlanInstkey);
		
		public void saveTestPlan(TestPlanBean testplanBean);
		public void saveTestPlanContent(TestPlanContent testPlanContent);
		
		public void updateTestPlan(TestPlanBean testPlanBean);
		
		public void deleteTestPlan(TestPlanBean testPlanBean);
		public void deleteTestPlanContent(TestPlanContent testPlanContent);
}
