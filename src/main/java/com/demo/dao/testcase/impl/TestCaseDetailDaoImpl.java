package com.demo.dao.testcase.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testcase.TestCaseDetailDao;
import com.demo.model.testcase.caseDetail;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("testCaseDetailDao")
public class TestCaseDetailDaoImpl extends BaseDaoImpl<caseDetail> implements TestCaseDetailDao  {
    public TestCaseDetailDaoImpl(){
        super(caseDetail.class);
    }

    @Override
    public caseDetail findCaseDetailByCaseinstkey(String key) {
        String hql = "from caseDetail  as t where t.caseId=?0 ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,key);
        return (caseDetail)query.getResultList().get(0);
    }
}
