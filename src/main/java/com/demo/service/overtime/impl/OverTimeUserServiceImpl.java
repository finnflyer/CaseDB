package com.demo.service.overtime.impl;

import com.demo.dao.overtime.OverTimeUserDao;
import com.demo.model.overtime.OverTimeUser;
import com.demo.service.overtime.OverTimeUserService;
import com.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by finnf on 2018/8/23.
 */
@Service("otUserService")
public class OverTimeUserServiceImpl extends BaseServiceImpl<OverTimeUser>  implements OverTimeUserService{

    @Autowired
    private OverTimeUserDao overTimeUserDao;

    public OverTimeUser findOverTimeUserByName(String userName){
        return overTimeUserDao.findUserByName(userName);
    }
}
