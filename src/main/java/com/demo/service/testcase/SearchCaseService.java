package com.demo.service.testcase;

import com.demo.model.testcase.SearchCaseBean;
import com.demo.service.BaseService;
import com.demo.util.QueryResult;

/**
 * Created by Admin on 2016/9/5.
 */
public interface SearchCaseService extends BaseService<SearchCaseBean> {
    QueryResult<SearchCaseBean> findResultByHql(String hql);
}
