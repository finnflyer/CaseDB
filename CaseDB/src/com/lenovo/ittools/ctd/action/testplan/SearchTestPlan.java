package com.lenovo.ittools.ctd.action.testplan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchTestPlan extends ActionSupport{
	private PageBean pageBean;
	private Integer page=1;
	private int searchflag;
	private TestPlanService testPlanService;
	private String testPlanOwner;
	private String testPlanName;
	
	public String execute() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
		if(userInfo ==null){
				throw new Exception();
		}
		StringBuffer hql = new StringBuffer(); 
		hql.append("from TestPlanBean as i where 1=1 and i. testPlanStatus='Active'  ");
		if(testPlanOwner!=null && !"".equals(testPlanOwner)){
			testPlanOwner = testPlanOwner.toUpperCase();
			hql.append("and upper(i.testPlanOwner) like '%"+testPlanOwner+"%'");
		}
		if(null !=testPlanName && (!"".equals(testPlanName))){
			testPlanName = testPlanName.toUpperCase();
			hql.append(" and upper(i.testPlanName) like '%"+testPlanName+"%'");
		}
		
		System.out.println(hql);
		if(1==searchflag){
			pageBean = testPlanService.findTestPlanBeansByCondition(hql.toString(), 30, page);
			session.setAttribute("ohql", hql.toString());
		}else{
			pageBean = testPlanService.findTestPlanBeansByCondition((String)session.getAttribute("ohql"), 30, page);
		}
		return SUCCESS;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
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

	public TestPlanService getTestPlanService() {
		return testPlanService;
	}

	public void setTestPlanService(TestPlanService testPlanService) {
		this.testPlanService = testPlanService;
	}

	public String getTestPlanOwner() {
		return testPlanOwner;
	}

	public void setTestPlanOwner(String testPlanOwner) {
		this.testPlanOwner = testPlanOwner;
	}

	public String getTestPlanName() {
		return testPlanName;
	}

	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}
	
}
