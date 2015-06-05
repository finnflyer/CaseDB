/**
 * 
 */
package com.lenovo.ittools.ctd.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Kylin Zhang 2012-2-27
 * @description 
 * 
 */
public class LoginAction extends ActionSupport {
		
	private UserService userService;
	public String userName;
	public String password;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * @author: Kylin Zhang 2012-2-27
	 * @description: 
	 * @param @return
	 * @param @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
	    UserInfoBean userInfo = new UserInfoBean();
        
	    User user = userService.loginUser(userName, password);
	    if(null == user){
	    	return ERROR;
	    }else{
	    	userInfo.setUserID(user.getInstkey());
	    	userInfo.setUserName(userName);
	       	session.setAttribute("userInfo", userInfo);
	    	
	    }
		return SUCCESS;
	}
}
