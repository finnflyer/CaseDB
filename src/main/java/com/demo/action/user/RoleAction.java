package com.demo.action.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.model.common.UserRoleBean;
import com.demo.service.common.UserService;
import com.demo.util.QueryResult;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/21.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class RoleAction extends ActionSupport {
    @Autowired
    private UserService userService;
    private JSONObject jsonString;
private String jsonTestString;

    public String getJsonTestString() {
        return jsonTestString;
    }

    public void setJsonTestString(String jsonTestString) {
        this.jsonTestString = jsonTestString;
    }

    public JSONObject getJsonString() {
        return jsonString;
    }

    public void setJsonString(JSONObject jsonString) {
        this.jsonString = jsonString;
    }

    private List<UserRoleBean> userRoleBeanList;
    private Integer pageNumber = 1;
    private Integer pageSize = 20;

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public List<UserRoleBean> getUserRoleBeanList() {
        return userRoleBeanList;
    }

    public void setUserRoleBeanList(List<UserRoleBean> userRoleBeanList) {
        this.userRoleBeanList = userRoleBeanList;
    }

    @Action(value = "GetUserList", results = {@Result(name = "success", type = "json", params = {"root", "jsonString"})})
    public String GetUserRoleList() {
        userRoleBeanList = new ArrayList<>();
        String[] params = {"1"};

        QueryResult<User> userQueryResult = userService.getScrollData(0 + (pageNumber - 1) * pageSize, pageSize);
        for (User temp : userQueryResult.getDatas()) {
            UserRoleBean userRoleBean = new UserRoleBean();
            userRoleBean.setInstkey(temp.getInstkey());
            userRoleBean.setUsername(temp.getUsername());
            userRoleBean.setRole(temp.getRole());
            if (temp.getMailbox() != null)
                userRoleBean.setMailbox(temp.getMailbox());
            userRoleBean.setLive(temp.getLive().toString());
            userRoleBeanList.add(userRoleBean);
        }
        System.out.println(userRoleBeanList.size());
        String outputString = JSONArray.toJSONString(userRoleBeanList);
        //JSONObject test = JSONObject.toJSON(userRoleBeanList);
        jsonString = new JSONObject();
       // JSONArray test = JSON.toJSON(userRoleBeanList);
        jsonString.put("rows",outputString);
        jsonString.put("total",pageSize);
        System.out.println(jsonString.toJSONString());
        jsonTestString = jsonString.toJSONString();

        return SUCCESS;
    }
}
