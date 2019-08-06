package com.demo.service.overtime.impl;

import com.demo.dao.overtime.ApplyOverTimeDao;
import com.demo.model.overtime.ApplyOTBean;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.overtime.ApplyOverTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by finnf on 2018/6/23.
 */
@Service("ApplyOverTimeService")
public class ApplyOverTimeServiceImpl extends BaseServiceImpl<ApplyOTBean> implements ApplyOverTimeService{
    @Autowired
    private ApplyOverTimeDao applyOverTimeDao;

   public List<ApplyOTBean> findApplyOTBean(String hql){
        return applyOverTimeDao.findApplyOTBean(hql);
    }
}
