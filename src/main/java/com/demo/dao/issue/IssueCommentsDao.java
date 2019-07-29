package com.demo.dao.issue;

import com.demo.model.issue.IssueComments;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
public interface IssueCommentsDao {
    List<IssueComments> findIssueCommentsByIssueKey(String key);
}
