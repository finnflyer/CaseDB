package com.demo.service.overtime;

import com.demo.model.overtime.OverTimeUser;
import com.demo.service.BaseService;

/**
 * Created by finnf on 2018/8/23.
 */
public interface OverTimeUserService extends BaseService<OverTimeUser> {
     OverTimeUser findOverTimeUserByName(String userName);
}
