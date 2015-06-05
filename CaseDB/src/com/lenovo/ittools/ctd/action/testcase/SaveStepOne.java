package com.lenovo.ittools.ctd.action.testcase;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.aop.ThrowsAdvice;

import com.lenovo.ittools.ctd.bean.testcase.CaseLanMap;
import com.lenovo.ittools.ctd.bean.testcase.LanguagesBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.common.UserInfoBean;
import com.lenovo.ittools.ctd.form.TCFormBean;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;
import com.lenovo.ittools.ctd.util.Generator;
import com.opensymphony.xwork2.ActionSupport;

public class SaveStepOne extends ActionSupport {
	private TCFormBean tcFormbean = TCFormBean.getInstance();
	private String caseInstkey;
	private String caseInfoInstkey;
	private TestCaseService tcService;
	private String caseName;
	private String caseCode;
	private String functionId;
	private String mapOs;
	private String testmodeId;
	private String mapBrand;
	private String version;
	private String introduction = "";
	private String modifyReason;
	private List<CaseLanMap> CaseLan;
	private List<LanguagesBean> LanBean;

	@SuppressWarnings("null")
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
		LanBean = tcService.findLanguagesBeansAll();
		if (userInfo == null) {
			return "logError";
	}
		mapOs = mapOs.replace(", ", "");
		mapBrand = mapBrand.replace(", ", "");
		TestCase testCase = new TestCase();
		caseInstkey = Generator.generatorID();
		testCase.setCaseInstkey(caseInstkey);
		testCase.setCaseCode(caseCode);
		testCase.setCaseName(caseName);
		testCase.setCreator(userInfo.getUserName());
		testCase.setOwner(userInfo.getUserName());
		testCase.setVersion(version);
		testCase.setDate(new Date());
		testCase.setStatus("Draft");
		tcService.saveTestCase(testCase);
		TestCaseInfo testCaseInfo = new TestCaseInfo();
		testCaseInfo.setBrandId(Integer.valueOf(mapBrand));
		testCaseInfo.setCaseInstkey(caseInstkey);
		caseInfoInstkey = Generator.generatorID();
		testCaseInfo.setCaseInfoInstkey(caseInfoInstkey);
		testCaseInfo.setFuncId(Integer.valueOf(functionId));
		testCaseInfo.setOsId(Integer.valueOf(mapOs));
		// testCaseInfo.setTestModeId(Integer.valueOf(testmodeId));
		testCaseInfo.setIntroduction(introduction);
		testCaseInfo.setModifyReason(modifyReason);
		tcService.saveTestCaseInfo(testCaseInfo);
		session.setAttribute("caseInstkey", caseInstkey);
		session.setAttribute("caseInfoInstkey", caseInfoInstkey);
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (null == caseName || "".equals(caseName)) {
			this.addFieldError("CaseName",
					" The CaseName field is required and should not be null");
		}
		if (null == caseCode || "".equals(caseCode)) {
			this.addFieldError("CaseCode",
					" The CaseCode field is required and should not be null");
		}
		if (null == version || "".equals(version)) {
			this.addFieldError("Version",
					" The Version field is required and should not be null");
		}
		if (null == modifyReason || "".equals(modifyReason)) {
			this.addFieldError("modifyReason",
					" The ModifyReason field is required and should not be null");
		}

	}

	public String getCaseInfoInstkey() {
		return caseInfoInstkey;
	}

	public void setCaseInfoInstkey(String caseInfoInstkey) {
		this.caseInfoInstkey = caseInfoInstkey;
	}

	public TCFormBean getTcFormbean() {
		return tcFormbean;
	}

	public void setTcFormbean(TCFormBean tcFormbean) {
		this.tcFormbean = tcFormbean;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCaseInstkey() {
		return caseInstkey;
	}

	public void setCaseInstkey(String caseInstkey) {
		this.caseInstkey = caseInstkey;
	}

	public TestCaseService getTcService() {
		return tcService;
	}

	public void setTcService(TestCaseService tcService) {
		this.tcService = tcService;
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

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

}
