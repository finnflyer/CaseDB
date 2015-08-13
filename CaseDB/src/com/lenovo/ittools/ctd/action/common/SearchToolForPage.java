package com.lenovo.ittools.ctd.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.common.TestToolService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchToolForPage extends ActionSupport{
	private PageBean pageBean;
	private String toolName;
	private TestToolService testToolService;
	private Integer page=1;
	private int searchflag;
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
		if(userInfo ==null){
				throw new Exception();
		}
		StringBuffer hql = new StringBuffer(); 
		hql.append("from TestToolBean as i where 1=1  and i.status='New' ");
		if(toolName!=null && !"".equals(toolName)){
			toolName = toolName.toUpperCase();
			hql.append("and upper(i.toolName) like '%"+toolName+"%'");
		}

		hql.append(" Order by i.uploadTime desc");
		if(1==searchflag){
			pageBean = testToolService.findTestToolBeansByCondition(hql.toString(), 30, page);
			session.setAttribute("ohql", hql.toString());
		}else{
			pageBean = testToolService.findTestToolBeansByCondition((String)session.getAttribute("ohql"), 30, page);
			
		}
		return SUCCESS;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public TestToolService getTestToolService() {
		return testToolService;
	}
	public void setTestToolService(TestToolService testToolService) {
		this.testToolService = testToolService;
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
	
	
}
