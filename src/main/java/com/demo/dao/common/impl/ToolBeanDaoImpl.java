package com.demo.dao.common.impl;

import com.demo.dao.common.ToolBeanDao;
import com.demo.dao.impl.BaseDaoImpl;
import com.demo.model.common.TestToolBean;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/9/9.
 */
@Repository("toolBeanDao")
public class ToolBeanDaoImpl extends BaseDaoImpl<TestToolBean>  implements ToolBeanDao{
    public ToolBeanDaoImpl(){super(TestToolBean.class);}
}
