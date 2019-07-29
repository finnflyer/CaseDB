package com.demo.service.testplan.impl;

import com.demo.dao.testplan.TestPlanDao;
import com.demo.model.testplan.TestPlanBean;
import com.demo.service.BaseService;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.testplan.TestPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/9/5.
 */
@Service("testPlanService")
public class TestPlanServiceImpl extends BaseServiceImpl<TestPlanBean> implements TestPlanService{
    @Autowired
    private TestPlanDao testplanDao;
}
