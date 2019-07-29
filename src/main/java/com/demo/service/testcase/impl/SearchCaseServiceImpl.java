package com.demo.service.testcase.impl;

import com.demo.dao.testcase.SearchDao;
import com.demo.model.testcase.SearchCaseBean;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.testcase.SearchCaseService;
import com.demo.util.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/9/5.
 */
@Service("searchCaseService")
public class SearchCaseServiceImpl extends BaseServiceImpl<SearchCaseBean> implements SearchCaseService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public QueryResult<SearchCaseBean> findResultByHql(String hql) {
        return searchDao.findResultByHql(hql);
    }
}
