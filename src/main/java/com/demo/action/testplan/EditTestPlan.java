package com.demo.action.testplan;

import com.demo.model.formbean.TCFormBean;
import com.demo.model.project.ProjectBean;
import com.demo.model.testcase.SearchCaseBean;
import com.demo.model.testplan.TestPlanBean;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.testcase.SearchCaseService;
import com.demo.service.testcase.TestCaseService;
import com.demo.service.testplan.TestPlanContentService;
import com.demo.service.testplan.TestPlanService;
import com.demo.util.QueryResult;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/8.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class EditTestPlan extends ActionSupport {
    @Autowired
    private TestPlanService testPlanService;
    @Autowired
    private TestPlanContentService testPlanContentService;
    @Autowired
    private SearchCaseService searchCaseService;
    @Autowired
    private TestCaseService testCaseService;
    private String testPlanInstkey;
    private List<TestPlanContent> tpContentList;
    private List<SearchCaseBean> contentList;
    private String testPlanName;
    private String projectInstkey;

    public String getProjectInstkey() {
        return projectInstkey;
    }

    public void setProjectInstkey(String projectInstkey) {
        this.projectInstkey = projectInstkey;
    }

    public String getTestPlanInstkey() {
        return testPlanInstkey;
    }

    public void setTestPlanInstkey(String testPlanInstkey) {
        this.testPlanInstkey = testPlanInstkey;
    }

    public List<TestPlanContent> getTpContentList() {
        return tpContentList;
    }

    public void setTpContentList(List<TestPlanContent> tpContentList) {
        this.tpContentList = tpContentList;
    }

    public List<SearchCaseBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<SearchCaseBean> contentList) {
        this.contentList = contentList;
    }

    public String getTestPlanName() {
        return testPlanName;
    }

    public void setTestPlanName(String testPlanName) {
        this.testPlanName = testPlanName;
    }

    @Action(value="EditTestPlan",results={@Result(name="success",location="/jsp/TestPlan/EditTestPlan.jsp")})
    public String editTestPlan(){
        testPlanName =  testPlanService.findById(testPlanInstkey).getTestplanname();
        tpContentList = testPlanContentService.findTestPlanContentByTestPlanKey(testPlanInstkey);
        contentList = new ArrayList<SearchCaseBean>();
        int count = 0;
        for(TestPlanContent temp:tpContentList){
            System.out.println(temp.getTestcaseinstkey());
            SearchCaseBean scb = searchCaseService.findById(temp.getTestcaseinstkey());
            if(scb!=null){
                int order = temp.getTporder();
                if(count!=0){
                    order = temp.getTporder()-count;
                }
                scb.setTpOrder(order);
                SearchBeanCatoSetting(scb);
                contentList.add(scb);
            }else{
                count+=1;
            }

        }
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

    private TCFormBean tcFormbean = TCFormBean.getInstance();


    public TCFormBean getTcFormbean() {
        return tcFormbean;
    }

    public void setTcFormbean(TCFormBean tcFormbean) {
        this.tcFormbean = tcFormbean;
    }

    @Action(value="preTestPlan",results={@Result(name="success",location="/jsp/TestPlan/SearchTestCaseForPlan.jsp")})
    public String preTestPlan(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("projectKey",projectInstkey);

        Map<Integer,String> osMap =testCaseService.findSupportOSAllForMap();
        tcFormbean.setMapOs(osMap);
        Map<Integer,String> brandMap = testCaseService.findTestCaseBrandAllForMap();
        tcFormbean.setMapBrand(brandMap);
        Map<Integer,String> functionMap = testCaseService.findTestCaseFunctionAllForMap();
        tcFormbean.setMapFunction(functionMap);
        Map<Integer,String> languageMap = testCaseService.findLanguageAllForMap();
        tcFormbean.setMapLanguage(languageMap);

        return SUCCESS;
    }

}
