package com.demo.action.issue;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.issue.IssueBean;
import com.demo.service.issue.IssueService;
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

@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class IssueViewJson  extends ActionSupport{
    @Autowired
    public IssueService issueService;
    private JSONObject dataOb;
    private String dataMap;
    public String issueStatus;

    @Action(value = "IssueViewJson", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String IssueAllViewJson(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String limit1 = request.getParameter("limit");
        int limit = Integer.parseInt(limit1);
        //获取已经显示的个数，然后除以一页的个数就是页数
        String offset1 = request.getParameter("offset");
        int offset = Integer.parseInt(offset1) / limit;
        String where = "upper(issueStatus) =?0" ;
        if(issueStatus==null)
            issueStatus="Open";
        String[] param = {issueStatus};
        QueryResult<IssueBean> issueBeanQueryResult = issueService.getScrollData(offset * limit, limit,where,param);
        JSONArray arr = new JSONArray();
        for(IssueBean temp:issueBeanQueryResult.getDatas()){
            JSONObject ob = new JSONObject();
            ob.put("Id", temp.getInstkey());
            ob.put("IssueName",temp.getIssueName());
            ob.put("IssueStatus",temp.getIssueStatus());
            ob.put("ECR",temp.getEcrNumber());
            ob.put("Description",temp.getDescription());
            arr.add(ob);
        }
        JSONObject ob = new JSONObject();
        ob.put("total", issueBeanQueryResult.getTotalCount());
        ob.put("rows", arr);
        dataOb = ob;
        dataMap = ob.toJSONString();
        return SUCCESS;
    }

    public JSONObject getDataOb() {
        return dataOb;
    }

    public String getDataMap() {
        return dataMap;
    }

    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }

    public void setDataOb(JSONObject dataOb) {
        this.dataOb = dataOb;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }
}
