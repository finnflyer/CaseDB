package com.demo.dao.testplan.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testplan.TestPlanDao;
import com.demo.model.testplan.TestPlanBean;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2016/9/5.
 */
@Repository("testPlanDao")
public class TestPlanDaoImpl extends BaseDaoImpl<TestPlanBean> implements TestPlanDao{
    public TestPlanDaoImpl(){super(TestPlanBean.class);}
}
