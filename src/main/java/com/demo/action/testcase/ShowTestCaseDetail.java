package com.demo.action.testcase;

import com.demo.model.testcase.*;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.CatoSetting;
import com.demo.util.EncryptUtil;
import com.demo.util.Generator;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/5.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class ShowTestCaseDetail extends ActionSupport {
    @Autowired
    private TestCaseService tcService;
    private TestCase testCase;
    private TestCaseInfo testCaseInfo;
    private List<CaseLanguage> CaseLan;
    private List<LanguagesBean> LanBean;
    private  List<TestCaseContent> testCaseContents;
    private String sid;
    @Action(value="ShowTestCaseDetail",results={@Result(name="success",location="/jsp/TestCases/ShowCaseDetail.jsp")})
    public String showCaseDetail(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();


        String caseInstkey;
        if(testCase.getCaseinstkey().length()>21)
            caseInstkey =  EncryptUtil.deString(testCase.getCaseinstkey());
        else
        caseInstkey = testCase.getCaseinstkey();

        testCase = tcService.findById(caseInstkey);
        testCaseInfo = tcService.findTestCaseInfoByCasekey(caseInstkey);
        testCaseContents = tcService.findCaseContentByCaseKey(caseInstkey);
        LanBean = tcService.findLanguageBeanAll();
        CaseLan = tcService.findCaseLanguageByCaseId(caseInstkey);
        Map<Integer,String> map = tcService.findLanguageAllForMap();
        for(CaseLanguage temp : CaseLan){
            temp.setLanvalue(map.get(temp.getLocalset()));
        }
        eachStringforHtml(testCaseContents);
        testCaseInfoCatoSetting(testCaseInfo);
        int time=0;
        for(TestCaseContent temp : testCaseContents){
            time = time +temp.getSteptime();
        }
        List<PictureBean>  pics=new ArrayList<PictureBean>();
        for(int i=0;i<testCaseContents.size();i++){
            TestCaseContent temp=testCaseContents.get(i);
            pics=tcService.findPictureByCaseContentkey(temp.getCasecontentinstkey());
            if(pics.size()>0){
                temp.setCasePics(pics);
            }
        }
        testCaseInfo.setExecutetime(time);
        sid = Generator.generatorID();

        Map sessionMap = new HashMap();
        sessionMap.put("caseInstkey", EncryptUtil.enString(caseInstkey));
        sessionMap.put("caseInfoInstkey", EncryptUtil.enString(testCaseInfo.getCaseinfoinstkey()));
        sessionMap.put("Mode","View");
        session.setAttribute(sid, sessionMap);
        return SUCCESS;
    }
    public void eachStringforHtml(List<TestCaseContent> testCaseContents){
             for(TestCaseContent temp:testCaseContents){
            temp.setComment(StringFormat.formatStrForHtml(temp.getComment()));
            temp.setTestitem(StringFormat.formatStrForHtml(temp.getTestitem()));
            temp.setTestresult(StringFormat.formatStrForHtml(temp.getTestresult()));
            temp.setTeststep(StringFormat.formatStrForHtml(temp.getTeststep()));
        }
    }
    public void testCaseInfoCatoSetting(TestCaseInfo testCaseInfo){
        Map<Integer,String>  functionMap = tcService.findTestCaseFunctionAllForMap();
        String testCaseFunction = functionMap.get(testCaseInfo.getFuncid());
        testCaseInfo.setFuncCato(testCaseFunction);
        testCaseInfo.setComments(StringFormat.formatStrForHtml(testCaseInfo.getComments()));
        testCaseInfo.setModifyreason(StringFormat.formatStrForHtml(testCaseInfo.getModifyreason()));
        testCaseInfo.setHardwareinfo(StringFormat.formatStrForHtml(testCaseInfo.getHardwareinfo()));
        testCaseInfo.setLanguagecomment(StringFormat.formatStrForHtml(testCaseInfo.getLanguagecomment()));
        CatoSetting.testCaseInfoSetting(testCaseInfo);
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

    public List<TestCaseContent> getTestCaseContents() {
        return testCaseContents;
    }

    public void setTestCaseContents(List<TestCaseContent> testCaseContents) {
        this.testCaseContents = testCaseContents;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
