package com.lenovo.ittools.ctd.dao.testcase;

import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;

public interface SearchCaseDAO {
		public SearchCaseBean findSearchCaseBeanByCaseInstkey(String caseInstkey);
		public List<SearchCaseBean> findByConditionForPage(final String hql, final int offset, final int length) throws Exception;
		public int getAllRowCount(String hql) throws Exception;
}
