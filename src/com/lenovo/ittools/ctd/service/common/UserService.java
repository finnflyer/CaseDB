package com.lenovo.ittools.ctd.service.common;

import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.common.PageBean;

public interface UserService {
	public User loginUser(String userName,String password);
	public void saveUser(User user) ;
	public PageBean findUserByConditionForPage(String hql, int pageSize,int page);
	public User findByID(String instkey);
	public void update(User user);
}
