package com.lenovo.ittools.ctd.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.service.common.UserService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport {

	public String userName;
	public String password;
	public String password1;
	public UserService userService;
	
	
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPassword1() {
		return password1;
	}



	public void setPassword1(String password1) {
		this.password1 = password1;
	}



	public UserService getUserService() {
		return userService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public String execute() throws Exception{

		if("".endsWith(userName)){
			return ERROR;
		}
        if(!password.equals(password1)){
        	return ERROR;
        }
		
	    User user = userService.loginUser(userName, password);
		if(null == user){
			User newUser = new User();
			newUser.setInstkey(Generator.generatorID());
			newUser.setActiveCode(String.valueOf(System.currentTimeMillis()));
			newUser.setPassword(password);
			newUser.setLive(1);
			newUser.setUsername(userName);
			userService.saveUser(newUser);	
		}else{
			return ERROR;
		}
		
		return SUCCESS;
	}
}
