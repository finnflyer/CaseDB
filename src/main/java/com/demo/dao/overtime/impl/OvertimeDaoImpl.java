package com.demo.dao.overtime.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.overtime.OvertimeDao;
import com.demo.model.overtime.OTBean;
import org.springframework.stereotype.Repository;

/**
 * Created by finnf on 2018/6/19.
 */
@Repository("OvertimeDao")
public class OvertimeDaoImpl extends BaseDaoImpl<OTBean> implements OvertimeDao {
    public OvertimeDaoImpl(){super(OTBean.class);}

}
