package com.demo.action.issue;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.model.issue.IssueComments;
import com.demo.service.issue.IssueCommentsService;
import com.demo.util.Generator;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 2016/9/10.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class CreateComments extends ActionSupport {
    @Autowired
    private IssueCommentsService issueCommentsService;

    private String issueKey;
    private String comments;
    private JSONObject dataMap;

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public JSONObject getDataMap() {
        return dataMap;
    }

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    @Action(value="CreateComment",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String createComments(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        IssueComments issueComments = new IssueComments();
        issueComments.setInstkey(Generator.generatorID());
        issueComments.setCreateBy(user.getUsername());
        issueComments.setComments(comments);
        issueComments.setIssueInstkey(issueKey);
        issueComments.setStatus("active");
        issueComments.setCreateDate(new Date());
        issueCommentsService.save(issueComments);

        this.dataMap = new JSONObject();
        this.dataMap.put("issueKey",issueKey);

        return SUCCESS;
    }

    private List<IssueComments> commentsList;

    public void setCommentsList(List<IssueComments> commentsList) {
        this.commentsList = commentsList;
    }

    public List<IssueComments> getCommentsList() {
        return commentsList;
    }

    @Action(value="ShowComments",results={@Result(name="success",type="json",params = {"root", "commentsList"})})
    public String showComment(){
        String[] param ={issueKey,"Del"};

        commentsList = issueCommentsService.getScrollData(0,10,"issueInstkey=?0 and status!=?1 ",param).getDatas();
        return SUCCESS;
    }


}
