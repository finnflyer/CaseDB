package com.demo.service.testcase.impl;

import com.demo.dao.testcase.TestCaseDetailDao;
import com.demo.model.testcase.caseDetail;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.testcase.TestCaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

@Service("testCaseDetailService")
public class TestCaseDetailServiceImpl extends BaseServiceImpl<caseDetail> implements TestCaseDetailService {
    @Autowired
    private TestCaseDetailDao testCaseDetailDao;

    @Override
    public caseDetail findTestCaseDetailByCaseInstkey(String key) {
        return testCaseDetailDao.findCaseDetailByCaseinstkey(key);
    }
}
