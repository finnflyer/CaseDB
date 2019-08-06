package com.demo.action.testplan;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.model.testcase.TestCase;
import com.demo.model.testplan.TestPlanBean;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.project.ProjectService;
import com.demo.service.testcase.TestCaseService;
import com.demo.service.testplan.TestPlanContentService;
import com.demo.service.testplan.TestPlanService;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */

@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class CreateTestPlan extends ActionSupport{
    @Autowired
    private TestPlanService testPlanService;
    @Autowired
    private TestPlanContentService caseContentService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TestCaseService testCaseService;



    private JSONObject dataMap;
    private String testPlanName;
    private String testPlanComments;
    private String str;
    @Action(value="CreateTestPlan",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String createTestPlan(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String projectInstkey =  (String) session.getAttribute("projectKey");
        TestPlanBean testPlanBean = new TestPlanBean();
        String testPlanInstkey = Generator.generatorID();

        testPlanBean.setTestplaninstkey(testPlanInstkey);
        testPlanBean.setTestplancomments(testPlanComments);
        testPlanBean.setTestplandescription("");
        testPlanBean.setCreatedate(new Date());
        testPlanBean.setProjectinstkey(projectInstkey);
        testPlanBean.setProjectname(projectService.findById(projectInstkey).getProjectName());
        testPlanBean.setTestplanownerinstkey(user.getInstkey());
        testPlanBean.setTestplanowner(user.getUsername());
        testPlanBean.setTestplanstatus("Active");
        testPlanBean.setTestplantype("Template");
        testPlanBean.setTestplanname(testPlanName);
        testPlanService.save(testPlanBean);

        String[] ary = str.split(" ");
        int i = 1;
        TestPlanContent tpContent = new TestPlanContent();


        for (int list = 1; list < ary.length; list++) {
            tpContent.setCreatetime(new Date());
            tpContent.setTestcaseinstkey(ary[list]);
            tpContent.setTestplancontentinstkey(Generator.generatorID());
            tpContent.setTestplaninstkey(testPlanInstkey);
            tpContent.setTporder(i);
            caseContentService.save(tpContent);
            i++;
        }
        List<TestPlanContent> tpc = caseContentService.findTestPlanContentByTestPlanKey(testPlanInstkey);
        Collections.sort(tpc, new Comparator<TestPlanContent>() {
            public int compare(TestPlanContent o1, TestPlanContent o2) {
                if (Integer.parseInt(getCaseCode(o2)) < Integer.parseInt(getCaseCode(o1))) {
                    return 1;
                }
                if (Integer.parseInt(getCaseCode(o2)) == Integer.parseInt(getCaseCode(o1))) {
                    return 0;
                }
                return -1;
            }
        });
        for(int j=0;j<tpc.size();j++){
            tpc.get(j).setTporder(j+1);
            caseContentService.update(tpc.get(j));
        }

        this.dataMap = new JSONObject();
        this.dataMap.put("projectKey",projectInstkey);
        session.removeAttribute("projectKey");
        return SUCCESS;
    }
    public String getCaseCode(TestPlanContent temp){

        TestCase testCase = testCaseService.findById(temp.getTestcaseinstkey());
        return testCase.getCasecode();
    }

    public JSONObject getDataMap() {
        return dataMap;
    }

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    public String getTestPlanName() {
        return testPlanName;
    }

    public void setTestPlanName(String testPlanName) {
        this.testPlanName = testPlanName;
    }

    public String getTestPlanComments() {
        return testPlanComments;
    }

    public void setTestPlanComments(String testPlanComments) {
        this.testPlanComments = testPlanComments;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
