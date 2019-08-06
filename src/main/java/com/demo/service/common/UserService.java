package com.demo.service.common;

import com.demo.model.common.User;
import com.demo.service.BaseService;

/**
 * Created by admin on 2016/8/31.
 */
public interface UserService extends BaseService<User> {
     User findByName(String Name);
     User findByNameAndPassword(User user);

}
