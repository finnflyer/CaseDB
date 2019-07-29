package com.demo.dao.testcase.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testcase.SearchDao;
import com.demo.model.testcase.*;
import com.demo.util.QueryResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2016/9/5.
 */
@Repository("searchDao")
public class SearchDaoImpl extends BaseDaoImpl<SearchCaseBean> implements SearchDao {
    public SearchDaoImpl(){
        super(SearchCaseBean.class);
    }

    @Override
    public QueryResult<SearchCaseBean> findResultByHql(String hql) {
        QueryResult<SearchCaseBean> resultList = new QueryResult<SearchCaseBean>();
        List<SearchCaseBean> list = (List<SearchCaseBean>)this.getSession().createQuery(hql).list();
        resultList.setTotalCount(list.size());
        resultList.setDatas(list);
        return resultList;
    }
}
