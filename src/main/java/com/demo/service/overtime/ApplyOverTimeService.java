package com.demo.service.overtime;

import com.demo.model.overtime.ApplyOTBean;
import com.demo.service.BaseService;

import java.util.List;

/**
 * Created by finnf on 2018/6/23.
 */
public interface ApplyOverTimeService extends BaseService<ApplyOTBean> {
    List<ApplyOTBean> findApplyOTBean(String hql);
}
