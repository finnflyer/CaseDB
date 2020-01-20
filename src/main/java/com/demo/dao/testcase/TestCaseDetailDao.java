package com.demo.dao.testcase;

import com.demo.model.testcase.caseDetail;

public interface TestCaseDetailDao {
    caseDetail findCaseDetailByCaseinstkey(String key);
}
