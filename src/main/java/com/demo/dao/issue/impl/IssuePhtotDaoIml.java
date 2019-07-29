package com.demo.dao.issue.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.issue.IssuePhotoDao;
import com.demo.model.issue.IssuePhotoBean;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2016/9/8.
 */
@Repository("issuePhotoDao")
public class IssuePhtotDaoIml extends BaseDaoImpl<IssuePhotoBean> implements IssuePhotoDao{
    public IssuePhtotDaoIml(){super(IssuePhotoBean.class);}
}
