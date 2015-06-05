package com.lenovo.ittools.ctd.action.testcase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.service.testcase.SearchCaseService;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchTestCase extends ActionSupport{

	private PageBean pageBean;
	private String caseName;
	private String caseOwner;
	private SearchCaseService scaseService;
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
		hql.append("from SearchCaseBean as i where 1=1  ");
		if(caseName!=null && !"".equals(caseName)){
			caseName = caseName.toUpperCase();
			hql.append("and upper(i.caseName) like '%"+caseName+"%'");
		}
		if(null !=caseOwner && (!"".equals(caseOwner))){
			caseOwner = caseOwner.toUpperCase();
			hql.append(" and upper(i.owner) like '%"+caseOwner+"%'");
		}
		hql.append(" Order by i.caseCode asc");
		System.out.println(hql);
		
		if(1==searchflag){
			pageBean = scaseService.findSearchCaseBeanByConditionForPage(hql.toString(), 30, page);
			List<SearchCaseBean> list = pageBean.getList();
			for(SearchCaseBean temp:list){
				SearchBeanCatoSetting(temp);
			}
			session.setAttribute("ohql", hql.toString());
		}
		else{
			pageBean = scaseService.findSearchCaseBeanByConditionForPage((String)session.getAttribute("ohql"), 30, page);
			List<SearchCaseBean> list = pageBean.getList();
			for(SearchCaseBean temp:list){
				SearchBeanCatoSetting(temp);
			}
		}
	    session.setAttribute("caseInstkey", "");
	    session.setAttribute("caseInfoInstkey", "");
    	return SUCCESS;
		
	}
	public void SearchBeanCatoSetting(SearchCaseBean temp){
		switch (temp.getBrandId()){
		 case 1:
			 temp.setBrandCato("ThinkPad");
         	break;
         case 12:
        	 temp.setBrandCato("ThinkPad/ThinkStation");
         	break;
         case 2:
        	 temp.setBrandCato("ThinkStation");
        	 break;
         }
		switch (temp.getOsId()) {
		  case 1:
			  temp.setOsCato("xp");
			break;
		  case 12:
			  temp.setOsCato("xp/win7");
			   break;
		  case 123:
			  temp.setOsCato("xp/win7/win8.x");
			   break;
		  case 1234:
			  temp.setOsCato("xp/win7/win8.x/win10");
			  break;
		  case 2:
			  temp.setOsCato("win7");
			  break;
		  case 23:
			  temp.setOsCato("win7/win8.x");
			  break;
		  case 234:
			  temp.setOsCato("win7/win8.x/win10");
			  break;
		  case 3:
			  temp.setOsCato("win8.x");
			  break;
		  case 34:
			  temp.setOsCato("win8.x/win10");
			  break;
		  case 4:
			  temp.setOsCato("win10");
			  break;
		default:
			break;
		}
	}
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseOwner() {
		return caseOwner;
	}

	public void setCaseOwner(String caseOwner) {
		this.caseOwner = caseOwner;
	}

	public SearchCaseService getScaseService() {
		return scaseService;
	}



	public void setScaseService(SearchCaseService scaseService) {
		this.scaseService = scaseService;
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
