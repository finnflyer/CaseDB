package com.lenovo.ittools.ctd.common;

import java.util.ArrayList;
import java.util.List;

public class UserInfoBean {
    private String userName = null;
    private String userID;
    private String Role;
    
    public UserInfoBean(){}
    
    
	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

    
}
