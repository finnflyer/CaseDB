package com.demo.action.abbreviation;

import com.demo.service.abbreviation.AbbreviationService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Created by finnf on 2018/8/17.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class AbbreviationAction extends ActionSupport{
    @Autowired
    public AbbreviationService abbreviationService;


    @Action(value = "AbbreviationHome", results = {@Result(name = "success", location = "/jsp/Abbreviation/AbbreviationHome.jsp")})
    public String AbbreviationHome(){
        return SUCCESS;
    }

    @Action(value = "AbbreviationEdit",results = {@Result(name="success",location = "/jsp/Abbreviation/EditAbbreviation.jsp")})
    public String AbbreviationEdit(){return SUCCESS;}

}
