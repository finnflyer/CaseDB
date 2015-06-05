package com.lenovo.ittools.ctd.action.testplan;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.form.TCFormBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;

public class PreCreateTestPlan extends ActionSupport {
	private TestCaseService tcService;
	private TCFormBean tcFormbean = TCFormBean.getInstance(); 
	

	public TCFormBean getTcFormbean() {
		return tcFormbean;
	}


	public void setTcFormbean(TCFormBean tcFormbean) {
		this.tcFormbean = tcFormbean;
	}


	public TestCaseService getTcService() {
		return tcService;
	}


	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}


	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");
		if (userInfo == null) {
			return "logError";
		}
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
}
