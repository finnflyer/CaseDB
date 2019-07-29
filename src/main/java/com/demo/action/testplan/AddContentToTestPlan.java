package com.demo.action.testplan;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.testcase.TestCase;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.testcase.TestCaseService;
import com.demo.service.testplan.TestPlanContentService;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2016/9/9.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class AddContentToTestPlan  extends ActionSupport{
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private TestPlanContentService caseContentService;
    private JSONObject dataMap;
    private String str;
    private String testPlanInstkey;

    public JSONObject getDataMap() {
        return dataMap;
    }

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getTestPlanInstkey() {
        return testPlanInstkey;
    }

    public void setTestPlanInstkey(String testPlanInstkey) {
        this.testPlanInstkey = testPlanInstkey;
    }

    @Action(value="AddContentToTestPlan",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String addContenttoTestPlan(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String projectInstkey =  (String) session.getAttribute("projectKey");
        List<TestPlanContent> tpc = caseContentService.findTestPlanContentByTestPlanKey(testPlanInstkey);
        String[] ary = str.split(" ");
        int i = 1;
        TestPlanContent tpContent = new TestPlanContent();
        for (int list = 1; list <= ary.length-1; list++) {
            tpContent.setCreatetime(new Date());
            tpContent.setTestcaseinstkey(ary[list]);
            tpContent.setTestplancontentinstkey(Generator.generatorID());
            tpContent.setTestplaninstkey(testPlanInstkey);
            tpContent.setTporder(i + tpc.size());
            caseContentService.save(tpContent);
            i++;
        }

        //Sort the Content
        tpc = caseContentService.findTestPlanContentByTestPlanKey(testPlanInstkey);
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


}
