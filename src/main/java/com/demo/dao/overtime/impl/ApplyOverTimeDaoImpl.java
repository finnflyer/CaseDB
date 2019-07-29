package com.demo.dao.overtime.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.overtime.ApplyOverTimeDao;
import com.demo.model.overtime.ApplyOTBean;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by finnf on 2018/6/23.
 */
@Repository("ApplyOverTimeDao")
public class ApplyOverTimeDaoImpl  extends BaseDaoImpl<ApplyOTBean> implements ApplyOverTimeDao{
    public ApplyOverTimeDaoImpl(){super(ApplyOTBean.class);}
    public List<ApplyOTBean> findApplyOTBean(String hql){
        Query query = this.getSession().createQuery(hql);
        return (List<ApplyOTBean>) query.list();
    }
}
