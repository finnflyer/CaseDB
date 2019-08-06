package com.demo.dao.overtime;

import com.demo.model.overtime.ApplyOTBean;

import java.util.List;

/**
 * Created by finnf on 2018/6/23.
 */
public interface ApplyOverTimeDao {
    List<ApplyOTBean> findApplyOTBean(String hql);
}
