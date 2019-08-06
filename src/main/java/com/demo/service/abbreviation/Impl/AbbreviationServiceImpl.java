package com.demo.service.abbreviation.Impl;

import com.demo.dao.abbreviation.AbbreviationDao;
import com.demo.model.abbreviation.AbbreviationBean;
import com.demo.service.abbreviation.AbbreviationService;
import com.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by finnf on 2018/8/17.
 */
@Service("abbService")
public class AbbreviationServiceImpl extends BaseServiceImpl<AbbreviationBean> implements AbbreviationService{
    @Autowired
    private AbbreviationDao abbreviationDao;
}
