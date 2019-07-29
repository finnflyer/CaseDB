package com.demo.action.issue;

import com.demo.model.issue.IssueComments;
import com.demo.service.issue.IssueCommentsService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class IssueCommentsAction extends ActionSupport {

    @Autowired
    private IssueCommentsService issueCommentsService;
    private IssueComments issueComments;
    private String commentsKey;

    public IssueComments getIssueComments() {
        return issueComments;
    }

    public void setIssueComments(IssueComments issueComments) {
        this.issueComments = issueComments;
    }

    public String getCommentsKey() {
        return commentsKey;
    }

    public void setCommentsKey(String commentsKey) {
        this.commentsKey = commentsKey;
    }

    @Action(value = "DeleteComments", results = {@Result(name = "success", type="redirect",location = "ShowIssueDetail",
            params={"issueKey","%{issueComments.issueInstkey}"})})
    public String DeleteIssuePhotoFile() {
        issueComments = issueCommentsService.findById(commentsKey);
        issueComments.setStatus("Del");
        issueCommentsService.update(issueComments);
        return SUCCESS;
    }


}
