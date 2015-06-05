package com.lenovo.ittools.ctd.action.common;

import java.util.List;

import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class EditUserRole extends ActionSupport {
		private String instkey;
		private UserService userService;
		private User user;
		private List<String> roleList;
		public String execute() throws Exception{
			 user = userService.findByID(instkey);	 
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
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public List<String> getRoleList() {
			return roleList;
		}
		public void setRoleList(List<String> roleList) {
			this.roleList = roleList;
		}
		
}
