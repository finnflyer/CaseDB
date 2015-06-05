/**
 * 
 */
package com.lenovo.ittools.ctd.bean.common;

/*
instkey	varchar
username	varchar
mailbox	varchar
role	string
employeeid	integer
description	varchar
live  integer
activecode varchar
 */


/**
 * @author Chill Huang 2015-03-20
 * @description 
 * 
 */
public class User {
	private String instkey;
	private String username;
	private String mailbox;
	private String role;
	private Integer employeeid;
	private String description;
	private String password;
	private Integer live;
	private String  activeCode;

	public String getInstkey() {
		return instkey;
	}
	public void setInstkey(String instkey) {
		this.instkey = instkey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getLive() {
		return live;
	}
	public void setLive(Integer live) {
		this.live = live;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	
}
