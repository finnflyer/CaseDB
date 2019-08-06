package com.demo.action.user;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Admin on 2016/9/1.
 */
@Namespace("/User")
@ParentPackage("json-default")
public class LogOutAction extends ActionSupport{
    private JSONObject dataMap;
    private static final long serialVersionUID = 1L;

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    public JSONObject getDataMap() {
        return dataMap;
    }

    @Action( value = "logOut",results = {
            @Result(name="success",type = "json", params = { "root", "dataMap" })
    })
    public String logOut(){
        this.dataMap = new JSONObject();
        dataMap.put("isSuccess", "true");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return SUCCESS;
    }
}
