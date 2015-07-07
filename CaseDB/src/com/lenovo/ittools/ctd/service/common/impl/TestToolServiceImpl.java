package com.lenovo.ittools.ctd.service.common.impl;

import java.util.List;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.dao.common.TestToolDAO;
import com.lenovo.ittools.ctd.service.common.TestToolService;

public class TestToolServiceImpl implements TestToolService {
	public TestToolDAO testToolDAO;
	public void save(TestToolBean toolBean) {
		this.testToolDAO.save(toolBean);
	}
	public void delete(TestToolBean toolBean) {
		this.testToolDAO.delete(toolBean);
	}
	public TestToolBean findTestToolBeanByInstkey(String instkey) {
		return testToolDAO.findTestToolBeanByInstkey(instkey);
	}
	public void update(TestToolBean toolBean) {
		this.testToolDAO.upload(toolBean);
	}
	public TestToolDAO getTestToolDAO() {
		return testToolDAO;
	}
	public void setTestToolDAO(TestToolDAO testToolDAO) {
		this.testToolDAO = testToolDAO;
	}
	public PageBean findTestToolBeansByCondition(String hql, int pageSize,
			int page) throws Exception {
		PageBean pageBean = new PageBean();
		try {
			int allRow = testToolDAO.getAllRowCount(hql);
			int totalPage = PageBean.countTotalPage(pageSize, allRow);
			final int offset = PageBean.countOffset(pageSize, page);
			final int length = pageSize;
			final int currentPage = PageBean.countCurrentPage(page);
			List<TestToolBean> list = testToolDAO.findByConditionForPage(hql, offset, length);
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

}
