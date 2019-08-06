package com.demo.dao.issue.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.issue.IssueCommentsDao;
import com.demo.model.issue.IssueComments;
import org.hibernate.query.Query;
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
        String hql = "from IssueComments as i where i.issueInstkey= ?0 and i.status!='Del' Order by i.instkey desc";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, key);
        System.out.println(query.getResultList());
        return (List<IssueComments>) query.getResultList();
    }
}
