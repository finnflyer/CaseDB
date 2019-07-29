package com.demo.dao.issue.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.issue.IssueDao;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssuePhaseBean;
import com.demo.model.testcase.TestCaseSupportOs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
@Repository("issueDao")
public class IssueDaoImpl extends BaseDaoImpl<IssueBean> implements IssueDao{
    public IssueDaoImpl(){super(IssueBean.class);}

    @Override
    public List<IssuePhaseBean> findIssuePhaseBeanAll() {
        String hql ="from IssuePhaseBean  as i where 1=1 order by  i.phaseId";
        return (List<IssuePhaseBean>) this.getSession().createQuery(hql).list();
    }

    @Override
    public List<IssueBean> findIssueBeanByProjectKey(String key) {
        String hql ="from IssueBean  as i where i.issueStatus!='Del' and i.projectinstkey='"+key+"'";
        return (List<IssueBean>) this.getSession().createQuery(hql).list();
    }
}
