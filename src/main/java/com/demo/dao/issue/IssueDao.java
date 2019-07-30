package com.demo.dao.issue;

import com.demo.model.issue.ComponetBean;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssuePhaseBean;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
public interface IssueDao {
      List<IssuePhaseBean> findIssuePhaseBeanAll();
      List<IssueBean> findIssueBeanByProjectKey(String key);
      List<ComponetBean> findComponentBeanAll();
}
