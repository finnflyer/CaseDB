package com.demo.action.overtime;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.overtime.OverTimeUser;
import com.demo.service.overtime.OverTimeUserService;
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

/**
 * Created by auto on 2018/9/2.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class OverTimeUserData extends ActionSupport{
    @Autowired
    public OverTimeUserService overTimeUserService;
    private String dataMap;
    private JSONObject dataOb;
    public String strJson;

    @Action(value="OverTimeUserViewJson",results =  {@Result(name="success",type ="json",params = {"root","dataOb"})})
    public String OverTimeExportJson(){
        HttpServletRequest request = ServletActionContext.getRequest();

        String limit1 = request.getParameter("limit");
        int limit = Integer.parseInt(limit1);
        //获取已经显示的个数，然后除以一页的个数就是页数
        String offset1 = request.getParameter("offset");
        int offset = Integer.parseInt(offset1) / limit;

       QueryResult<OverTimeUser> queryResult = overTimeUserService.getScrollData(offset * limit, limit);
        JSONArray arr = new JSONArray();
        for(OverTimeUser temp:queryResult.getDatas()){
            JSONObject ob = new JSONObject();
            ob.put("id",temp.getInstkey());
            ob.put("Employid",temp.getEmployid());
            ob.put("LoginName",temp.getLoginname());
            ob.put("UserName",temp.getUsername());
            arr.add(ob);
        }
        //获取数据库中所有的数据
        JSONObject ob = new JSONObject();
        ob.put("total", queryResult.getTotalCount());
        ob.put("rows", arr);
        dataOb = ob;
        dataMap = ob.toJSONString();
        return SUCCESS;
    }
    @Action(value = "EditOverTimeUser", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String EditOverTime() {
        System.out.println(strJson);
        JSONObject ob = JSONObject.parseObject(strJson);
        OverTimeUser overTimeUser = overTimeUserService.findById(ob.getString("id"));
        overTimeUser.setEmployid(ob.getString("Employid"));
        overTimeUser.setUsername(ob.getString("UserName"));
        overTimeUserService.update(overTimeUser);
        return SUCCESS;
    }

    @Action(value = "DeleteOverTimeUser", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String DeleteOverTime() {
        JSONObject ob = JSONObject.parseObject(strJson);
        OverTimeUser overTimeUser = overTimeUserService.findById(ob.getString("id"));
        overTimeUserService.delete(overTimeUser);
        return SUCCESS;
    }
    public String getStrJson() {
        return strJson;
    }

    public void setStrJson(String strJson) {
        this.strJson = strJson;
    }

    public JSONObject getDataOb() {
        return dataOb;
    }

    public void setDataOb(JSONObject dataOb) {
        this.dataOb = dataOb;
    }

    public String getDataMap() {
        return dataMap;
    }

    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }
}
