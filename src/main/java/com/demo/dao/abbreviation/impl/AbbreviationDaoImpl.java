package com.demo.dao.abbreviation.impl;

import com.demo.dao.abbreviation.AbbreviationDao;
import com.demo.dao.impl.BaseDaoImpl;
import com.demo.model.abbreviation.AbbreviationBean;
import org.springframework.stereotype.Repository;

/**
 * Created by finnf on 2018/8/17.
 */
@Repository("abbBeanDao")
public class AbbreviationDaoImpl extends BaseDaoImpl<AbbreviationBean> implements AbbreviationDao{

    public AbbreviationDaoImpl() {super(AbbreviationBean.class);}
}
