package com.demo.dao.overtime;

import com.demo.model.overtime.OverTimeUser;

/**
 * Created by finnf on 2018/8/23.
 */
public interface OverTimeUserDao {
     OverTimeUser findUserByName(String userName);
}
