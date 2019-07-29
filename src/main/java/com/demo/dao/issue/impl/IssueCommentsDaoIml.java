package com.demo.dao.issue.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.issue.IssueCommentsDao;
import com.demo.model.issue.IssueComments;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
@Repository("issueCommentsDao")
public class IssueCommentsDaoIml extends BaseDaoImpl<IssueComments> implements IssueCommentsDao {
    public IssueCommentsDaoIml(){super(IssueComments.class);}

    @Override
    public List<IssueComments> findIssueCommentsByIssueKey(String key) {
        String hql = "from IssueComments as i where i.issueInstkey='"+key+"'and i.status!='Del' order by  i.instkey desc";
        return (List<IssueComments>) this.getSession().createQuery(hql).list();
    }
}
