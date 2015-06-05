package com.lenovo.ittools.ctd.action.common;

import com.lenovo.ittools.ctd.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class PreHome extends ActionSupport {
     private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    public String execute(){
    	return SUCCESS;
    }
}
