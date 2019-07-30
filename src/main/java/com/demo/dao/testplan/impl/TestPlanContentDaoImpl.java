package com.demo.dao.testplan.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testplan.TestPlanContentDao;
import com.demo.model.testplan.TestPlanContent;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2016/9/5.
 */
@Repository("testPlanContentDao")
public class TestPlanContentDaoImpl extends BaseDaoImpl<TestPlanContent> implements TestPlanContentDao {
    public  TestPlanContentDaoImpl() {super(TestPlanContent.class);}

    @Override
    public List<TestPlanContent> findTestPlanContentByTestPlanKey(String key) {
        String hql = "from TestPlanContent as tpc where tpc.testplaninstkey=?0 order by tpc.tporder asc";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,key);
        return (List<TestPlanContent>) query.getResultList();
    }
}
