package com.demo.action.testcase;

import com.demo.model.common.User;
import com.demo.model.testcase.CaseLanguage;
import com.demo.model.testcase.LanguagesBean;
import com.demo.model.testcase.TestCase;
import com.demo.model.testcase.TestCaseInfo;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/9/3.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class SaveTestCaseFirstStep extends ActionSupport {

    private String caseName;
    private String caseCode;
    private String functionId;
    private String mapOs;
    private String mapBrand;
    private String mapTestMode;
    private String version;
    private String introduction = "";
    private String modifyReason;
    private List<CaseLanguage> CaseLan;
    private List<LanguagesBean> LanBean;

    @Autowired
    private TestCaseService testCaseService;

    @Action(value = "SaveStepOne", results = {@Result(name = "success", location = "/jsp/TestCases/StepTwo.jsp")
            , @Result(name = "input", location = "/jsp/TestCases/StepOne.jsp")})
    public String SaveStepOne() {
        LanBean = testCaseService.findLanguageBeanAll();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        mapOs = mapOs.replace(", ", "");
        mapBrand = mapBrand.replace(", ", "");
        mapTestMode = mapTestMode.replace(", ","");
        TestCase testCase = new TestCase();
        String caseInstkey = Generator.generatorID();
        testCase.setCaseinstkey(caseInstkey);
        testCase.setCasecode(caseCode);
        testCase.setCasename(caseName);
        testCase.setCreator(user.getUsername());
        testCase.setOwner(user.getUsername());
        testCase.setVersion(version);
        testCase.setDate(new Date());
        testCase.setStatus("Draft");
        testCaseService.save(testCase);
        TestCaseInfo testCaseInfo = new TestCaseInfo();
        testCaseInfo.setBrandid(Integer.valueOf(mapBrand));
        testCaseInfo.setTestmodeid(Integer.valueOf(mapTestMode));
        testCaseInfo.setCaseinstkey(caseInstkey);
        String caseInfoInstkey = Generator.generatorID();
        testCaseInfo.setCaseinfoinstkey(caseInfoInstkey);
        testCaseInfo.setFuncid(Integer.valueOf(functionId));
        testCaseInfo.setOsid(mapOs);
        testCaseInfo.setIntroduction(introduction);
        testCaseInfo.setModifyreason(modifyReason);
        testCaseService.saveTestCaseInfo(testCaseInfo);

        Map sessionMap = new HashMap();
        sessionMap.put("caseInstkey", caseInstkey);
        sessionMap.put("caseInfoInstkey", caseInfoInstkey);
        session.setAttribute("CreateCaseMode", sessionMap);
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

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason;
    }


    public List<CaseLanguage> getCaseLan() {
        return CaseLan;
    }

    public void setCaseLan(List<CaseLanguage> caseLan) {
        CaseLan = caseLan;
    }

    public List<LanguagesBean> getLanBean() {
        return LanBean;
    }

    public void setLanBean(List<LanguagesBean> lanBean) {
        LanBean = lanBean;
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


    public String getMapBrand() {
        return mapBrand;
    }

    public void setMapBrand(String mapBrand) {
        this.mapBrand = mapBrand;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setMapTestMode(String mapTestMode) {
        this.mapTestMode = mapTestMode;
    }

    public String getMapTestMode() {
        return mapTestMode;
    }
}
