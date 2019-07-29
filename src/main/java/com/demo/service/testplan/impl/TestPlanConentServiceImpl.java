package com.demo.service.testplan.impl;

import com.demo.dao.testplan.TestPlanContentDao;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.testplan.TestPlanContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2016/9/5.
 */
@Service("caseContentService")
public class TestPlanConentServiceImpl extends BaseServiceImpl<TestPlanContent> implements TestPlanContentService{
    @Autowired
    private TestPlanContentDao testPlanContentDao;

    @Override
    public List<TestPlanContent> findTestPlanContentByTestPlanKey(String key) {
        return testPlanContentDao.findTestPlanContentByTestPlanKey(key);
    }
}
