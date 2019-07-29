package com.demo.service.issue;

import com.demo.model.issue.IssueComments;
import com.demo.service.BaseService;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
public interface IssueCommentsService extends BaseService<IssueComments> {
    List<IssueComments> findIssueCommentsByIssueKey(String key);
}
