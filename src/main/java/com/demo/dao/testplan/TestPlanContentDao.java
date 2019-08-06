package com.demo.dao.testplan;

import com.demo.model.testplan.TestPlanContent;

import java.util.List;

/**
 * Created by Admin on 2016/9/5.
 */
public interface TestPlanContentDao {
    List<TestPlanContent> findTestPlanContentByTestPlanKey(String key);
}
