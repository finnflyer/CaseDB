package com.demo.dao.testcase;

import com.demo.model.testcase.SearchCaseBean;
import com.demo.util.QueryResult;

public interface SearchDao {
    QueryResult<SearchCaseBean> findResultByHql(String hql);
}
