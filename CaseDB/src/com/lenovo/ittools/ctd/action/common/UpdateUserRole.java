package com.lenovo.ittools.ctd.action.common;

import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserRole  extends ActionSupport{
		private String instkey;
		private UserService userService;
		private String role;
		public String execute() {
			User user = userService.findByID(instkey);
			user.setRole(role);
			userService.update(user);
			return SUCCESS;
		}
		public String getInstkey() {
			return instkey;
		}
		public void setInstkey(String instkey) {
			this.instkey = instkey;
		}
		public UserService getUserService() {
			return userService;
		}
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
}
