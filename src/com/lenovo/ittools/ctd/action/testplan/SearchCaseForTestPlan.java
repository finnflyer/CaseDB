package com.lenovo.ittools.ctd.action.testplan;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.common.PageBean;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.form.TCFormBean;
import com.lenovo.ittools.ctd.service.testcase.SearchCaseService;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.service.testplan.TestPlanService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.operations.And;

public class SearchCaseForTestPlan extends ActionSupport {

	private PageBean pageBean;
	private String caseName;
	private String caseOwner;
	private String mapOs;
	private String mapFunction;
	private String mapBrand;
	private SearchCaseService scaseService;
	private List<TestPlanBean> testPlanList;
	private Integer page=1;
	private int searchflag;
	private TestPlanService testPlanService;
	private TestCaseService tcService;
	private TCFormBean tcFormbean = TCFormBean.getInstance(); 
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
		if(userInfo ==null){
				throw new Exception();
		}

		testPlanList = testPlanService.findTestPlanBeansAll();
		StringBuffer hql = new StringBuffer(); 
		hql.append("from SearchCaseBean as i where 1=1 and ");
		if(null != mapOs  && !"".equals(mapOs)){
			mapOs = mapOs.replace(", ", " ");
			String[] aryFunc  = mapOs.split(" ");
			if(aryFunc.length>0){
				for(int i=0;i<=aryFunc.length-1;i++){
					hql.append(" i.osId like '%"+aryFunc[i]+"%' and");
				}
				hql.append(" 1=1 and ");
			}
			
		}
		if(null !=mapBrand && !"".equals(mapBrand)){
			mapBrand = mapBrand.replace(", ", " ");
			String[] aryFunc  = mapBrand.split(" ");
			if(aryFunc.length>0){
				for(int i=0;i<=aryFunc.length-1;i++){
					hql.append(" i.brandId like '%"+aryFunc[i]+"%' and");
				}
				hql.append(" 1=1 and (");
			}
		}
		if(null != mapFunction  && !"".equals(mapFunction)){
			mapFunction = mapFunction.replace(", ", " ");
			String[] aryFunc  = mapFunction.split(" ");
			   if(aryFunc.length>0){
		        	for(int i = 0;i<aryFunc.length-1;i++){
		        		hql.append(" i.funcId="+Integer.parseInt(aryFunc[i])+" or");
		        	}
		        	hql.append(" i.funcId="+Integer.parseInt(aryFunc[aryFunc.length-1])+") and ");    	
		        }
		}
		
	
     
        
        hql.append("  1= 1  " );

		System.out.println(hql);
		
		if(1==searchflag){
			pageBean = scaseService.findSearchCaseBeanByConditionForPage(hql.toString(), 100, page);
			List<SearchCaseBean> list = pageBean.getList();
			for(SearchCaseBean temp:list){
				SearchBeanCatoSetting(temp);
			}
			session.setAttribute("ohql", hql.toString());
		}
		else{
			pageBean = scaseService.findSearchCaseBeanByConditionForPage((String)session.getAttribute("ohql"), 100, page);
			List<SearchCaseBean> list = pageBean.getList();
			for(SearchCaseBean temp:list){
				SearchBeanCatoSetting(temp);
			}
		}
	    session.setAttribute("caseInstkey", "");
	    session.setAttribute("caseInfoInstkey", "");
		Map<Integer, String> functionMap = tcService.findTestcaseFuntionAllForMap();
		tcFormbean.setMapFunction(functionMap);

		Map<Integer, String> brandMap = tcService.findTestcaseBrandAllForMap();
		tcFormbean.setMapBrand(brandMap);
		Map<Integer, String> osMap = tcService.findTestcaseSupportOSAllForMap();
		tcFormbean.setMapOs(osMap);
		
		Map<Integer, String> tmMap= tcService.findTestcaseTestModeAllForMap();
		tcFormbean.setMapTestMode(tmMap);
		
		Map<Integer, String> lanMap=tcService.findLanguageBeanAllForMap();
		tcFormbean.setMapLanguage(lanMap);
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
	public List<TestPlanBean> getTestPlanList() {
		return testPlanList;
	}
	public void setTestPlanList(List<TestPlanBean> testPlanList) {
		this.testPlanList = testPlanList;
	}
	public TestPlanService getTestPlanService() {
		return testPlanService;
	}
	public void setTestPlanService(TestPlanService testPlanService) {
		this.testPlanService = testPlanService;
	}
	public String getMapOs() {
		return mapOs;
	}
	public void setMapOs(String mapOs) {
		this.mapOs = mapOs;
	}
	public String getMapFunction() {
		return mapFunction;
	}
	public void setMapFunction(String mapFunction) {
		this.mapFunction = mapFunction;
	}
	public String getMapBrand() {
		return mapBrand;
	}
	public void setMapBrand(String mapBrand) {
		this.mapBrand = mapBrand;
	}
	public TestCaseService getTcService() {
		return tcService;
	}
	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}
	public TCFormBean getTcFormbean() {
		return tcFormbean;
	}
	public void setTcFormbean(TCFormBean tcFormbean) {
		this.tcFormbean = tcFormbean;
	}
	
	
}
