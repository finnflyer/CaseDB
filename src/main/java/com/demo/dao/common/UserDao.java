package com.demo.dao.common;


import com.demo.model.common.User;


/**
 * Created by admin on 2016/8/31.
 */

public interface UserDao  {
     User findByName(String Name);
     User findByNameAndPassword(User user);
}
