package com.lenovo.ittools.ctd.dao.common;

import java.util.List;

import com.lenovo.ittools.ctd.bean.common.User;

public interface UserDAO {
	public User loginUser(String userName,String password);
	public void saveUser(User user) ;
	public User findByID(String instkey) ;
	public int getAllRowCount(String hql) throws Exception;
	public List<User> findUserByConditionForPage(final String hql, final int offset, final int length) throws Exception;
	public void updateUser(User user);
}
