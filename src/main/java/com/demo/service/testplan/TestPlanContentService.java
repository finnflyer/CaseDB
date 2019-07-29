package com.demo.service.testplan;

import com.demo.model.testplan.TestPlanContent;
import com.demo.service.BaseService;

import java.util.List;

/**
 * Created by Admin on 2016/9/5.
 */
public interface TestPlanContentService extends BaseService<TestPlanContent> {
    List<TestPlanContent> findTestPlanContentByTestPlanKey(String key);
}
