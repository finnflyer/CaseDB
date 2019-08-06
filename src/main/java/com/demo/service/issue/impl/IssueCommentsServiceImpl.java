package com.demo.service.issue.impl;

import com.demo.dao.issue.IssueCommentsDao;
import com.demo.model.issue.IssueComments;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.issue.IssueCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
@Service("issueCommentsService")
public class IssueCommentsServiceImpl extends BaseServiceImpl<IssueComments> implements IssueCommentsService {
    @Autowired
    private IssueCommentsDao issueCommentsDao;

    @Override
    public List<IssueComments> findIssueCommentsByIssueKey(String key) {
        return issueCommentsDao.findIssueCommentsByIssueKey(key);
    }
}
