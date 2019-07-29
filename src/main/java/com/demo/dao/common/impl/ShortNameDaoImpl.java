package com.demo.dao.common.impl;

import com.demo.dao.common.ShortNameDao;
import com.demo.dao.impl.BaseDaoImpl;
import com.demo.model.common.ShortNameBean;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2017/1/20.
 */
@Repository("shortNameDao")
public class ShortNameDaoImpl extends BaseDaoImpl<ShortNameBean> implements ShortNameDao {
    public ShortNameDaoImpl() {super(ShortNameBean.class);}
}
