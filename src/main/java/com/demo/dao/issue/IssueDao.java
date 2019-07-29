package com.demo.dao.issue;

import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssuePhaseBean;
import com.demo.model.testcase.TestCaseSupportOs;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
public interface IssueDao {
      List<IssuePhaseBean> findIssuePhaseBeanAll();
      List<IssueBean> findIssueBeanByProjectKey(String key);
}
