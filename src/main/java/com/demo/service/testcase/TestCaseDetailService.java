package com.demo.service.testcase;

import com.demo.model.testcase.caseDetail;
import com.demo.service.BaseService;

public interface TestCaseDetailService extends BaseService<caseDetail> {
    caseDetail findTestCaseDetailByCaseInstkey(String key);
}
