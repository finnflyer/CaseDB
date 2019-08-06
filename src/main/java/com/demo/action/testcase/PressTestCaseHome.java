package com.demo.action.testcase;

import com.demo.model.common.User;
import com.demo.model.formbean.TCFormBean;
import com.demo.service.testcase.TestCaseService;
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
import java.util.Map;

/**
 * Created by Admin on 2016/9/2.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class PressTestCaseHome extends ActionSupport {
    @Autowired
    private TestCaseService testCaseService;

    @Action(value = "TestCaseHome", results = {@Result(name = "success",type="redirect",location = "SearchCaseForPage")})
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("ViewCaseMode","");
        session.setAttribute("EditCaseMode","");



        return SUCCESS;
    }


}
