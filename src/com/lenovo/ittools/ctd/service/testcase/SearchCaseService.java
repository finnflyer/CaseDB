package com.lenovo.ittools.ctd.service.testcase;

import java.util.List;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.common.PageBean;

public interface SearchCaseService {
	public SearchCaseBean findSearchCaseBeanByCaseInstkey(String caseInstkey);
	public PageBean findSearchCaseBeanByConditionForPage(String hql, int pageSize,int page);

}
