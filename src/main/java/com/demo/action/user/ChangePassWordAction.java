package com.demo.action.user;

import com.demo.model.common.User;
import com.demo.service.common.UserService;
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

/**
 * Created by Admin on 2016/12/1.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class ChangePassWordAction extends ActionSupport {
    @Autowired
    private UserService userService;

    @Action(value = "showChangePWPage", results = {@Result(name = "success", location = "/jsp/user/ChangePassword.jsp")})
    public String showChangePWPage(){
        return SUCCESS;
    }


    private String password2;
    private String password3;

    @Action(value = "ChangePassword", results = {@Result(name = "success",type="redirect" ,location = "/index.jsp")})
    public String changePassword(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        user.setPassword(password2);
        userService.update(user);
        session.removeAttribute("user");
        return SUCCESS;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword3() {
        return password3;
    }

    public void setPassword3(String password3) {
        this.password3 = password3;
    }
}
