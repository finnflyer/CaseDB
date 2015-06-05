package com.lenovo.ittools.ctd.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchUserRole extends ActionSupport {
	private String userName;
	private String userRole;
	private UserService userService;
	private Integer page=1;
	private int searchflag;
	private PageBean pageBean;
	public String execute() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
		if(userInfo ==null){
				throw new Exception();
		}
		StringBuffer hql = new StringBuffer(); 
		hql.append("from User as u where 1=1");
		if(userName!=null && !"".equals(userName)){
			userName = userName.toUpperCase();
			hql.append("and upper(u.username) like '%"+userName+"%'");
		}
		if(userRole!=null && !"".equals(userRole)){
			userRole = userRole.toUpperCase();
			hql.append("and upper(u.role) like '%"+userRole+"%'");
		}
		System.out.println(hql);
		
		if(1==searchflag){
			pageBean = userService.findUserByConditionForPage(hql.toString(), 30, page);
			session.setAttribute("ohql", hql.toString());
		}else {
			pageBean  = userService.findUserByConditionForPage((String)session.getAttribute("ohql"), 30, page);
		}
		return SUCCESS;
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getSearchflag() {
		return searchflag;
	}
	public void setSearchflag(int searchflag) {
		this.searchflag = searchflag;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
}
