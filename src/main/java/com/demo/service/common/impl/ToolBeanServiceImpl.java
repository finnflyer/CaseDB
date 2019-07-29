package com.demo.service.common.impl;

import com.demo.dao.common.ToolBeanDao;
import com.demo.model.common.TestToolBean;
import com.demo.service.common.ToolBeanService;
import com.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/9/9.
 */
@Service("toolBeanService")
public class ToolBeanServiceImpl extends BaseServiceImpl<TestToolBean> implements ToolBeanService {
    @Autowired
    private ToolBeanDao toolBeanDao;
}
