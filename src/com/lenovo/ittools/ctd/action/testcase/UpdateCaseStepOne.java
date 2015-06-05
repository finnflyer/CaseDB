package com.lenovo.ittools.ctd.action.testcase;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lenovo.ittools.ctd.bean.testcase.CaseLanMap;
import com.lenovo.ittools.ctd.bean.testcase.LanguagesBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.form.TCFormBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCaseStepOne extends ActionSupport {
	private TCFormBean tcFormbean = TCFormBean.getInstance();
	private TestCaseService tcService;
	private String caseInstkey;
	private String caseInfoInstkey;
	private TestCase testCase;
	private TestCaseInfo testCaseInfo;
	private String mapOs;
	private String testmodeId;
	private String mapBrand;
	private List<CaseLanMap> CaseLan;
	private List<LanguagesBean> LanBean;
	private String functionId;
	private String caseName;
	private String caseCode;
	private String modifyReason;
	private String version;
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
		if (userInfo == null) {
			return "logError";
	}
		caseInfoInstkey = (String) session.getAttribute("caseInfoInstkey");
		caseInstkey = (String) session.getAttribute("caseInstkey");
		mapOs = mapOs.replace(", ", "");
		mapBrand = mapBrand.replace(", ", "");
		testCase = tcService.findTestCaseByCaseInstkey(caseInstkey);
		testCaseInfo = tcService.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
		
		testCase.setCaseCode(caseCode);
		testCase.setCaseName(caseName);
		testCase.setCreator(userInfo.getUserName());
		testCase.setOwner(userInfo.getUserName());
		testCase.setVersion(version);
		testCase.setDate(new Date());
		
		testCaseInfo.setFuncId(Integer.valueOf(functionId));
		testCaseInfo.setModifyReason(modifyReason);
		testCaseInfo.setBrandId(Integer.valueOf(mapBrand));
		testCaseInfo.setOsId(Integer.valueOf(mapOs));
		
		tcService.updateTestCaseInfo(testCaseInfo);
		tcService.updateTestCase(testCase);
		LanBean = tcService.findLanguagesBeansAll();
		CaseLan = tcService.findCaseLanMapsByCaseInstkey(caseInstkey);
		
		return SUCCESS;
	}

	public TestCaseService getTcService() {
		return tcService;
	}


	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
	}


	public String getCaseInstkey() {
		return caseInstkey;
	}


	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}


	public String getCaseInfoInstkey() {
		return caseInfoInstkey;
	}


	public void setCaseInfoInstkey(String caseInfoInstkey) {
		this.caseInfoInstkey = caseInfoInstkey;
	}


	public TestCase getTestCase() {
		return testCase;
	}


	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}


	public TestCaseInfo getTestCaseInfo() {
		return testCaseInfo;
	}


	public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
		this.testCaseInfo = testCaseInfo;
	}


	public String getMapOs() {
		return mapOs;
	}


	public void setMapOs(String mapOs) {
		this.mapOs = mapOs;
	}


	public String getTestmodeId() {
		return testmodeId;
	}


	public void setTestmodeId(String testmodeId) {
		this.testmodeId = testmodeId;
	}


	public String getMapBrand() {
		return mapBrand;
	}


	public void setMapBrand(String mapBrand) {
		this.mapBrand = mapBrand;
	}


	public List<CaseLanMap> getCaseLan() {
		return CaseLan;
	}


	public void setCaseLan(List<CaseLanMap> caseLan) {
		CaseLan = caseLan;
	}


	public List<LanguagesBean> getLanBean() {
		return LanBean;
	}


	public void setLanBean(List<LanguagesBean> lanBean) {
		LanBean = lanBean;
	}

	public TCFormBean getTcFormbean() {
		return tcFormbean;
	}

	public void setTcFormbean(TCFormBean tcFormbean) {
		this.tcFormbean = tcFormbean;
	}
	
	
}
