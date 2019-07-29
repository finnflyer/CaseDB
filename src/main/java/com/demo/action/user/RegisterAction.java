package com.demo.action.user;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.service.common.UserService;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Admin on 2016/9/1.
 */
@ParentPackage("json-default")
@Namespace("/User")
@Results( { @Result(name = ActionSupport.SUCCESS, type = "json"),
        @Result(name = ActionSupport.ERROR, type = "json") })
public class RegisterAction extends ActionSupport {
    @Autowired
    private UserService userService;
    private String userName;
    private String password;
    private JSONObject dataMap;
    private static final long serialVersionUID = 1L;

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    public JSONObject getDataMap() {
        return dataMap;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    @Action(value = "userRegAction", results = { @Result(type = "json", params = { "root", "dataMap" }) })
    public String userRegAction() throws Exception{
        this.dataMap = new JSONObject();
        dataMap.put("isSuccess", "true");
        User user = userService.findByName(userName);
        if(user!=null) return ERROR;
        user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setInstkey(Generator.generatorID());
        userService.saveID(user);
        return SUCCESS;
    }
}
