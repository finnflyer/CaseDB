package com.demo.service.common.impl;

import com.demo.dao.common.ShortNameDao;
import com.demo.model.common.ShortNameBean;
import com.demo.service.common.ShortNameService;
import com.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2017/1/20.
 */
@Service("shortNameService")
public class ShortNameServiceImpl extends BaseServiceImpl<ShortNameBean> implements ShortNameService {
    @Autowired
    private ShortNameDao shortNameDao;
}
