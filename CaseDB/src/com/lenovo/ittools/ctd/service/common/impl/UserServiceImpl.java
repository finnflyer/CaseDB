package com.lenovo.ittools.ctd.service.common.impl;

import java.util.List;

import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.dao.common.UserDAO;
import com.lenovo.ittools.ctd.service.common.UserService;

public class UserServiceImpl implements UserService{
    public UserDAO userDAO; 
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User loginUser(String userName, String password) {
		return userDAO.loginUser(userName, password);
	}

	public void saveUser(User user) {
          this.userDAO.saveUser(user);  	
	}

	public User findByID(String instkey) {
	     return userDAO.findByID(instkey);
	}

	public PageBean findUserByConditionForPage(String hql, int pageSize,
			int page) {
		PageBean pageBean = new PageBean();
		try {
			int allRow = userDAO.getAllRowCount(hql);
			int totalPage = PageBean.countTotalPage(pageSize, allRow);
			final int offset = PageBean.countOffset(pageSize, page);
			final int length = pageSize;
			final int currentPage = PageBean.countCurrentPage(page);
			List<User>  list = userDAO.findUserByConditionForPage(hql, offset, length);
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	public void update(User user) {
		this.userDAO.updateUser(user);
	}

}
