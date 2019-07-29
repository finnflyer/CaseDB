package com.demo.service.issue;

import com.demo.model.issue.IssueBean;
import com.demo.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/8.
 */
public interface IssueService extends BaseService<IssueBean> {
    Map<Integer,String> findIssuePhaseBeanForMap();
    List<IssueBean> findIssueBeanByProjectKey(String key);
}
