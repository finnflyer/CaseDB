package com.lenovo.ittools.ctd.service.testplan.impl;

import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.dao.testplan.TestPlanDao;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;

public class TestPlanServiceImpl implements TestPlanService{
		private TestPlanDao testPlanDAO;

		public TestPlanDao getTestPlanDAO() {
			return testPlanDAO;
		}

		public void setTestPlanDAO(TestPlanDao testPlanDAO) {
			this.testPlanDAO = testPlanDAO;
		}

		public List<TestPlanBean> findTestPlanBeansAll() {
			return this.testPlanDAO.findTestPlanBeansAll();
		}

		public void saveTestPlan(TestPlanBean testplanBean) {
			this.testPlanDAO.saveTestPlan(testplanBean);
			
		}

		public void saveTestPlanContent(TestPlanContent testPlanContent) {
			this.testPlanDAO.saveTestPlanContent(testPlanContent);
		}

		public void updateTestPlan(TestPlanBean testPlanBean) {
			this.testPlanDAO.updateTestPlan(testPlanBean);
		}

		public List<TestPlanContent> findTestPlanContentsByTestPlanInstkey(
				String testPlanInstkey) {
				return this.testPlanDAO.findTestPlanContentsByTestPlanInstkey(testPlanInstkey);
		}

		public PageBean findTestPlanBeansByCondition(String hql,
				int pageSize, int page) throws Exception {
			PageBean pageBean = new PageBean();
			try {
				int allRow = testPlanDAO.getAllRowCount(hql);
				int totalPage = PageBean.countTotalPage(pageSize, allRow);
				final int offset = PageBean.countOffset(pageSize, page);
				final int length = pageSize;
				final int currentPage = PageBean.countCurrentPage(page);
				
				List<TestPlanBean> list = testPlanDAO.findByConditionForPage(hql, offset, length);
				
				pageBean.setPageSize(pageSize);
				pageBean.setCurrentPage(currentPage);
				pageBean.setAllRow(allRow);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(list);
				pageBean.init();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pageBean;
		}

		public void deleteTestPlanContent(TestPlanContent testPlanContent) {
			this.testPlanDAO.deleteTestPlanContent(testPlanContent);
			
		}

		public TestPlanBean findTestPlanBeanByTestPlanInstkey(
				String testPlanInstkey) {
		return this.testPlanDAO.findTestPlanBeanByTestPlanInstkey(testPlanInstkey);
		}
		
}
