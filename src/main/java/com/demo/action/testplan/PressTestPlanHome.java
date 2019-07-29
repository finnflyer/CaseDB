package com.demo.action.testplan;

import com.demo.model.formbean.PageBean;
import com.demo.model.testplan.TestPlanBean;
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
import java.util.LinkedHashMap;

/**
 * Created by admin on 2016/9/6.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class PressTestPlanHome extends ActionSupport {

    @Action(value = "PressTestPlanHome", results = {@Result(name = "success", location = "/jsp/TestPlan/TPHome.jsp")})
    public String pressTPHome() {
        return SUCCESS;
    }
    @Autowired
    private TestPlanService tpService;
    private String testPlanOwner;
    private String testPlanName;
    private PageBean pageBean;
    private int pageNumber = 1;
    int pageSize = 30;
    private int searchflag = 1;
    public String getTestPlanName() {
        return testPlanName;
    }

    public void setTestPlanName(String testPlanName) {
        this.testPlanName = testPlanName;
    }

    public String getTestPlanOwner() {
        return testPlanOwner;
    }

    public void setTestPlanOwner(String testPlanOwner) {
        this.testPlanOwner = testPlanOwner;
    }

    @Action(value = "SearchTestPlan", results = {@Result(name = "suceess", location = "/jsp/TestPlan/TPHome.jsp")})
    public String SearchTestPlan() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("createdate ", "desc");
        String where = "upper(testplanname) like ?0 and upper(testplanowner) like ?1";
        String[] param = {"%%", "%%"};
        if (testPlanName != null && !"".equals(testPlanName))
            param[0] = "%" + testPlanName.toUpperCase() + "%";
        if (null != testPlanOwner && (!"".equals(testPlanOwner)))
            param[1] = "%" + testPlanOwner.toUpperCase() + "%";
        if (searchflag == 1)
            session.setAttribute("ohql", param);
        else
            param = (String[]) session.getAttribute("ohql");
        QueryResult<TestPlanBean> queryResult = tpService.getScrollData(0+pageSize*(pageNumber-1),30,where,param,orderby);
        pageBean = pageBeanInital(queryResult);
        return SUCCESS;
    }
    public PageBean pageBeanInital(QueryResult<TestPlanBean> resultQurey) {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));

        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;

    }


}
