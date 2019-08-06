package com.demo.dao.overtime.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.overtime.OverTimeUserDao;

import com.demo.model.overtime.OverTimeUser;
import org.springframework.stereotype.Repository;

/**
 * Created by finnf on 2018/8/23.
 */
@Repository("otUserDao")
public class OverTimeUserDaoImpl extends BaseDaoImpl<OverTimeUser> implements OverTimeUserDao{
    public OverTimeUserDaoImpl(){super(OverTimeUser.class);}

    public OverTimeUser findUserByName(String userName){
        String hql = "from OverTimeUser where loginname = ?0";

        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setParameter(0, userName);
        if(query.list().size()==0)
            return null;
        return (OverTimeUser) query.uniqueResult();
    }
}
