package com.demo.action.testplan;

import com.demo.model.testcase.SearchCaseBean;
import com.demo.model.testcase.TestCaseInfo;
import com.demo.model.testplan.TestPlanBean;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.testcase.SearchCaseService;
import com.demo.service.testcase.TestCaseService;
import com.demo.service.testplan.TestPlanContentService;
import com.demo.service.testplan.TestPlanService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/6.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class TestPlanDetailAction extends ActionSupport {

    @Autowired
    private TestPlanContentService testPlanContentService;
    @Autowired
    private TestPlanService testPlanService;
    @Autowired
    private SearchCaseService searchCaseService;
    @Autowired
    private TestCaseService testCaseService;

    private String testPlanInstkey;
    private List<TestPlanContent> tpContentList;
    private List<SearchCaseBean> contentList;
    private String totalTime;
    private String testPlanName;
    private TestPlanBean testPlan;

    public String getTestPlanInstkey() {
        return testPlanInstkey;
    }

    public void setTestPlanInstkey(String testPlanInstkey) {
        this.testPlanInstkey = testPlanInstkey;
    }

    public List<SearchCaseBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<SearchCaseBean> contentList) {
        this.contentList = contentList;
    }

    public List<TestPlanContent> getTpContentList() {
        return tpContentList;
    }

    public void setTpContentList(List<TestPlanContent> tpContentList) {
        this.tpContentList = tpContentList;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getTestPlanName() {
        return testPlanName;
    }

    public void setTestPlanName(String testPlanName) {
        this.testPlanName = testPlanName;
    }

    public TestPlanBean getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlanBean testPlan) {
        this.testPlan = testPlan;
    }

    @Action(value="ViewTestPlan",results={@Result(name="success",location="/jsp/TestPlan/ShowTestPlan.jsp")})
    public String viewTestPlan(){
        tpContentList = testPlanContentService.findTestPlanContentByTestPlanKey(testPlanInstkey);
        testPlan = testPlanService.findById(testPlanInstkey);
        testPlanName = testPlanService.findById(testPlanInstkey).getTestplanname();

        contentList = new ArrayList();
        int total=0;
        for(TestPlanContent temp:tpContentList){
            SearchCaseBean scb = searchCaseService.findById(temp.getTestcaseinstkey());
            if(scb!=null){
                TestCaseInfo testcaseInfo = testCaseService.findTestCaseInfoByCaseKey(temp.getTestcaseinstkey());
                total = total + testcaseInfo.getExecutetime();
                scb.setTpOrder(temp.getTporder());
                SearchBeanCatoSetting(scb);
                contentList.add(scb);
            }
        }

        totalTime = String.valueOf(total);
        return SUCCESS;
    }
    public void SearchBeanCatoSetting(SearchCaseBean temp) {
        switch (temp.getBrandid()) {
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
        switch (Integer.parseInt(temp.getOsid())) {
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


    @Action(value="DeleteTestPlan",results={@Result(name="success",location="/jsp/Project/ProjectHome.jsp")})
    public String deleteTestPlan(){
        TestPlanBean testPlanBean = testPlanService.findById(testPlanInstkey);
        testPlanBean.setTestplanstatus("Del");
        testPlanService.update(testPlanBean);
        return SUCCESS;
    }
}
