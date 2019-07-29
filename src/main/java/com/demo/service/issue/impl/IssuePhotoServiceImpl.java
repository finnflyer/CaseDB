package com.demo.service.issue.impl;

import com.demo.dao.issue.IssuePhotoDao;
import com.demo.model.issue.IssuePhotoBean;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.issue.IssuePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/9/8.
 */
@Service("issuePhotoService")
public class IssuePhotoServiceImpl extends BaseServiceImpl<IssuePhotoBean> implements IssuePhotoService {
@Autowired
private IssuePhotoDao issuePhotoDao;
}
