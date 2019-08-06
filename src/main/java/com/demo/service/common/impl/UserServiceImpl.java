package com.demo.service.common.impl;

import com.demo.dao.common.UserDao;
import com.demo.model.common.User;
import com.demo.service.common.UserService;
import com.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/8/31.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    @Autowired
    private UserDao userDao;
    //从容器中注入session工厂【无需get,set方法】
    @Override
    public User findByName(String Name){
        return userDao.findByName(Name);
    }

    @Override
    public User findByNameAndPassword(User user) {
        return userDao.findByNameAndPassword(user);
    }
}
