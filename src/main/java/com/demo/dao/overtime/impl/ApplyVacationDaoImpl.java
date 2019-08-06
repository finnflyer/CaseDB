package com.demo.dao.overtime.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.overtime.ApplyVacationDao;
import com.demo.model.overtime.ApplyVacationBean;
import org.springframework.stereotype.Repository;

/**
 * Created by finnf on 2018/6/23.
 */
@Repository("ApplyVacationDao")
public class ApplyVacationDaoImpl extends BaseDaoImpl<ApplyVacationBean>  implements ApplyVacationDao{
    public ApplyVacationDaoImpl(){super(ApplyVacationBean.class);}
}
