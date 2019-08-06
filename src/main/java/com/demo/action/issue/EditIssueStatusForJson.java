package com.demo.action.issue;

import com.demo.model.formbean.EditIssueBean;
import com.demo.model.issue.IssueBean;
import com.demo.service.issue.IssueService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/9/13.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class EditIssueStatusForJson extends ActionSupport {
    @Autowired
    private IssueService issueService;

    private List<IssueBean> issueBeanList;
    private String projectKey;
    private List<EditIssueBean> issueList;

    public List<EditIssueBean> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<EditIssueBean> issueList) {
        this.issueList = issueList;
    }

    public void setIssueBeanList(List<IssueBean> issueBeanList) {
        this.issueBeanList = issueBeanList;
    }

    public List<IssueBean> getIssueBeanList() {
        return issueBeanList;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectKey() {
        return projectKey;
    }

    @Action(value="EditIssueForJson",results={@Result(name="success",type="json",params = {"root", "issueList"})})
    public String EditComments(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String key = request.getParameter("aaaa");
        String[] param ={projectKey};
        issueBeanList = issueService.getScrollData(0,100,"projectinstkey=?0",param).getDatas();
        issueList = new ArrayList<EditIssueBean>();
        for(IssueBean temp:issueBeanList){
            EditIssueBean editIssueBean = new EditIssueBean();
            editIssueBean.setIssueName(temp.getIssueName());
            editIssueBean.setIssueEcr(temp.getEcrNumber());
            editIssueBean.setIssueStatus(temp.getIssueStatus());
            issueList.add(editIssueBean);
        }
        return SUCCESS;
    }


}
