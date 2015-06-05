package com.lenovo.ittools.ctd.service.testcase.impl;

import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;

import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.dao.testcase.SearchCaseDAO;
import com.lenovo.ittools.ctd.service.testcase.SearchCaseService;

public class SearchCaseServiceImpl implements SearchCaseService {
		private SearchCaseDAO searchCaseDAO;

		public SearchCaseDAO getSearchCaseDAO() {
			return searchCaseDAO;
		}

		public void setSearchCaseDAO(SearchCaseDAO searchCaseDAO) {
			this.searchCaseDAO = searchCaseDAO;
		}

		public SearchCaseBean findSearchCaseBeanByCaseInstkey(String caseInstkey) {
			return this.searchCaseDAO.findSearchCaseBeanByCaseInstkey(caseInstkey);
		}


		public PageBean findSearchCaseBeanByConditionForPage(String hql,
				int pageSize, int page) {
			PageBean pageBean = new PageBean();
			try {
				int allRow = searchCaseDAO.getAllRowCount(hql);
				int totalPage = PageBean.countTotalPage(pageSize, allRow);
				final int offset = PageBean.countOffset(pageSize, page);
				final int length = pageSize;
				final int currentPage = PageBean.countCurrentPage(page);
				
				List<SearchCaseBean> list = searchCaseDAO.findByConditionForPage(hql, offset, length);
				
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
