package com.demo.action.testcase;

import com.demo.model.formbean.TCFormBean;
import com.demo.model.testcase.TestCase;
import com.demo.model.testcase.TestCaseInfo;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.EncryptUtil;
import com.demo.util.StringFormat;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2016/9/6.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class EditTestCase extends ActionSupport {
    @Autowired
    private TestCaseService tcService;
    private TestCase testCase;
    private TestCaseInfo testCaseInfo;
    private String  caseKey;
    private String  sid;
    private TCFormBean tcFormbean = TCFormBean.getInstance();

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCaseKey() {
        return caseKey;
    }

    public void setCaseKey(String caseKey) {
        this.caseKey = caseKey;
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

    public TCFormBean getTcFormbean() {
        return tcFormbean;
    }

    public void setTcFormbean(TCFormBean tcFormbean) {
        this.tcFormbean = tcFormbean;
    }

    @Action(value="EditCaseStepOne",results={@Result(name="success",location="/jsp/TestCases/EditTestCaseStepOne.jsp")})
    public String EditCase() throws Exception{
        Map<Integer,String> osMap =tcService.findSupportOSAllForMap();
        tcFormbean.setMapOs(osMap);
        Map<Integer,String> brandMap = tcService.findTestCaseBrandAllForMap();
        tcFormbean.setMapBrand(brandMap);
        Map<Integer,String> functionMap = tcService.findTestCaseFunctionAllForMap();
        tcFormbean.setMapFunction(functionMap);
        Map<Integer,String> languageMap = tcService.findLanguageAllForMap();
        tcFormbean.setMapLanguage(languageMap);
        Map<Integer,String> mapTestMode = tcService.findTestCaseModeAllForMap();
        tcFormbean.setMapTestMode(mapTestMode);

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        Map sessionMap = (HashMap) session.getAttribute(sid);
        caseKey = EncryptUtil.deString(sessionMap.get("caseInstkey").toString());
        testCase = tcService.findById(caseKey);
        testCaseInfo = tcService.findTestCaseInfoByCaseKey(caseKey);
        testCaseInfo.setComments(StringFormat.formatStrForHtml(testCaseInfo.getComments()));
        testCaseInfo.setModifyreason(StringFormat.formatStrForHtml(testCaseInfo.getModifyreason()));
        testCaseInfo.setHardwareinfo(StringFormat.formatStrForHtml(testCaseInfo.getHardwareinfo()));
        testCaseInfo.setLanguagecomment(StringFormat.formatStrForHtml(testCaseInfo.getLanguagecomment()));

        sessionMap.put("caseInstkey",EncryptUtil.enString(caseKey));
        sessionMap.put("caseInfoInstkey", EncryptUtil.enString(testCaseInfo.getCaseinfoinstkey()));
        sessionMap.put("Mode","Edit");
        return SUCCESS;
    }
}
