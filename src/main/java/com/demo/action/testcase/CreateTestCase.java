package com.demo.action.testcase;

import com.demo.model.common.User;
import com.demo.model.formbean.TCFormBean;
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


import java.util.Map;

/**
 * Created by admin on 2016/9/3.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class CreateTestCase extends ActionSupport {
    private TCFormBean tcFormbean = TCFormBean.getInstance();


    @Autowired
    private TestCaseService testCaseService;
    @Action(value="CreateTestCase",results={@Result(name="success",location = "/jsp/TestCases/StepOne.jsp")
                                          })
    public String CreateTestCase(){


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


    public TCFormBean getTcFormbean() {
        return tcFormbean;
    }

    public void setTcFormbean(TCFormBean tcFormbean) {
        this.tcFormbean = tcFormbean;
    }
}
