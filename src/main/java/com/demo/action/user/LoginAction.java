package com.demo.action.user;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.service.common.UserService;
import com.opensymphony.xwork2.ActionContext;
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
 * Created by Admin on 2016/9/1.
 */

@Namespace("/User")
@Scope("prototype")
@ParentPackage("json-default")
public class LoginAction extends ActionSupport {

    @Autowired
    private UserService userService;
    private String userName;
    private String password;
    private String vali;
    private static final long serialVersionUID = 1L;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getVali() {
        return vali;
    }

    public void setVali(String vali) {
        this.vali = vali;
    }

    private JSONObject dataMap;

    public JSONObject getDataMap() {
        return dataMap;
    }

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    @Action(value = "LoginUser", results = {
            @Result(name = "success", type = "json", params = {"root", "dataMap"})
    })
    public String LoginAction() {
        System.out.println("Login action Start");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        this.dataMap = new JSONObject();
        String valiCode =ActionContext.getContext().getSession().get("random").toString();
        valiCode = valiCode.toLowerCase();
        if(!vali.toLowerCase().equals(valiCode)){
            dataMap.put("isSuccess", "3");
            ActionContext.getContext().getSession().remove("random");
            return SUCCESS;
        }

        User loginUser = new User();
        loginUser.setUsername(userName);
        loginUser.setPassword(password);
        User user = userService.findByName(userName);
        if (user != null){
            ActionContext.getContext().getSession().remove("random");
            dataMap.put("isSuccess", "2");
        }

        else{
            dataMap.put("isSuccess", "0");
            ActionContext.getContext().getSession().remove("random");
        }
         user = userService.findByNameAndPassword(loginUser);
        if (user != null) {
            dataMap.put("isSuccess", "1");
            session.setAttribute("user", user);
        }else{
            ActionContext.getContext().getSession().remove("random");
        }

        return SUCCESS;

    }





}
