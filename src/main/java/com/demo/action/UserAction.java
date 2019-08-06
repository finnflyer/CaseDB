package com.demo.action;

import com.demo.model.common.User;
import com.demo.service.common.UserService;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Created by admin on 2016/8/31.
 */

@Namespace("/user")
@ParentPackage("json-default")
@Results({ @Result(name = "success", location = "/msg.jsp"),
        @Result(name = "error", location = "/error.jsp") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class UserAction  extends ActionSupport{
    @Autowired
    private UserService userService;

    @Action(value = "saveUser", results = { @Result(name = "success", location = "/index.jsp") })
    public String saveUser(){
        User user = new User();
        user.setInstkey(Generator.generatorID());
        user.setEmployeeid(1);
        user.setMailbox("Test");
        user.setPassword("sadfadfs");
        userService.save(user);
        return SUCCESS;
    }

}
