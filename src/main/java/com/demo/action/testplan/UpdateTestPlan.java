package com.demo.action.testplan;

import com.demo.model.testcase.SearchCaseBean;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.testplan.TestPlanContentService;
import com.demo.service.testplan.TestPlanService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 2016/9/8.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class UpdateTestPlan extends ActionSupport {

    @Autowired
    private TestPlanContentService testPlanContentService;
    private String testPlanInstkey;
    private List<SearchCaseBean> conList;
    private String projectKey;

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getTestPlanInstkey() {
        return testPlanInstkey;
    }

    public void setTestPlanInstkey(String testPlanInstkey) {
        this.testPlanInstkey = testPlanInstkey;
    }

    public List<SearchCaseBean> getConList() {
        return conList;
    }

    public void setConList(List<SearchCaseBean> conList) {
        this.conList = conList;
    }
    @Action(value="UpdateTestPlan",results={@Result(name="success",type="redirect",location="/phase4/ShowProjectDetail?projectKey=${projectKey}")})
    public String UpdateTestPlan(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        projectKey =  (String) session.getAttribute("projectKey");
        if (conList != null) {
            //sort conlist by order
            List<SearchCaseBean> tpList = new ArrayList<SearchCaseBean>();
            int tpOrder =1;
            int size = 0;

            for(SearchCaseBean temp: conList){
                if(temp!=null){
                    size++;
                }
            }
            System.out.println(size);
            while(tpOrder<size+1){
                for(SearchCaseBean temp: conList){
                    if(temp!=null){
                        if(tpOrder==temp.getTpOrder()){
                            tpList.add(temp);
                            tpOrder++;
                        }

                    }
                }
            }

            List<TestPlanContent> list = testPlanContentService.findTestPlanContentByTestPlanKey(testPlanInstkey);
            for (TestPlanContent temp : list) {
                testPlanContentService.delete(temp);
            }
            int order = 1;
            int j = 0;

            for(SearchCaseBean temp : tpList){
                TestPlanContent tempContent = new TestPlanContent();
                tempContent.setTestplancontentinstkey(Generator
                        .generatorID());
                tempContent.setTestcaseinstkey(temp
                        .getCaseinstkey());
                tempContent.setTporder(temp.getTpOrder());
                tempContent.setCreatetime(new Date());
                tempContent.setTestplaninstkey(testPlanInstkey);
               testPlanContentService.save(tempContent);
            }
        }
        session.removeAttribute("projectKey");
        return SUCCESS;
    }

}
