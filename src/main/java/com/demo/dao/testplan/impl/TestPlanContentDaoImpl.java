package com.demo.dao.testplan.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testplan.TestPlanContentDao;
import com.demo.model.testplan.TestPlanContent;
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
        String hql = "from TestPlanContent as tpc where tpc.testplaninstkey='"+key+"' order by tpc.tporder asc";
        return (List<TestPlanContent>) this.getSession().createQuery(hql).list();
    }
}
