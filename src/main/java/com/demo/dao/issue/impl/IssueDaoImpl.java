package com.demo.dao.issue.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.issue.IssueDao;
import com.demo.model.issue.ComponetBean;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssuePhaseBean;
import com.demo.model.testcase.TestCaseSupportOs;
import org.hibernate.query.Query;
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
      //  String hql ="from IssuePhaseBean  as i where 1=1 order by  i.phaseId";
        String hql = "from IssuePhaseBean as i where 1=1 Order by i.phaseId";
        Query query = getSession().createQuery(hql);
        System.out.println(query.getResultList());
        return (List<IssuePhaseBean>) query.getResultList();
    }

    @Override
    public List<IssueBean> findIssueBeanByProjectKey(String key) {
       // String hql ="from IssueBean  as i where i.issueStatus!='Del' and i.projectinstkey='"+key+"'";
        String hql = "from IssueBean as i where i.projectinstkey= ?0 and i.issueStatus!='Del'";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, key);
        return (List<IssueBean>) query.getResultList();
    }

    @Override
    public List<ComponetBean> findComponentBeanAll() {
        String hql = "from ComponetBean as i where 1=1 Order by i.componet";
        Query query = getSession().createQuery(hql);
        System.out.println(query.getResultList());
        return (List<ComponetBean>) query.getResultList();
    }

}
