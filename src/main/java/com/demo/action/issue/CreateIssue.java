package com.demo.action.issue;

import com.demo.model.formbean.IssueFormBean;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssueComments;
import com.demo.model.issue.IssuePhotoBean;
import com.demo.service.issue.IssueCommentsService;
import com.demo.service.issue.IssuePhotoService;
import com.demo.service.issue.IssueService;
import com.demo.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/8.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class CreateIssue extends ActionSupport {
    private IssueFormBean issueFormBean = IssueFormBean.getInstance();
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private IssueService issueService;

    private String projectKey;


    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public IssueFormBean getIssueFormBean() {
        return issueFormBean;
    }

    public void setIssueFormBean(IssueFormBean issueFormBean) {
        this.issueFormBean = issueFormBean;
    }

    @Action(value = "CreateIssue", results = {@Result(name = "success", location = "/jsp/Issue/CreateIssue.jsp")})
    public String IssueCreate(){
        Map<Integer,String> osMap =testCaseService.findSupportOSAllForMap();
        Map<Integer,String> phaseMap = issueService.findIssuePhaseBeanForMap();
        issueFormBean.setMapOs(osMap);
        issueFormBean.setMapPhase(phaseMap);
        Map testSiteMap= new LinkedHashMap();
        testSiteMap.put(1,"CDL");
        testSiteMap.put(2,"LCFC");
        testSiteMap.put(3,"Compal");
        testSiteMap.put(4,"Wistron");
        testSiteMap.put(5,"Quata");
        testSiteMap.put(6,"Foxcom");
        issueFormBean.setMapTestsite(testSiteMap);
        return SUCCESS;
    }
    private IssueBean issueBean;
    @Autowired
    private IssueCommentsService issueCommentsService;
    @Autowired
    private IssuePhotoService issuePhotoService;
    private List<IssuePhotoBean> photoList;
    private List<IssueComments> commentsList;

    public List<IssuePhotoBean> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<IssuePhotoBean> photoList) {
        this.photoList = photoList;
    }

    public List<IssueComments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<IssueComments> commentsList) {
        this.commentsList = commentsList;
    }

    public void setIssueBean(IssueBean issueBean) {
        this.issueBean = issueBean;
    }

    public IssueBean getIssueBean() {
        return issueBean;
    }
    private String issueKey;

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    @Action(value="EditIssue",results={@Result(name="success",location="/jsp/Issue/EditIssue.jsp")})
    public String EditIssue(){
        issueBean = issueService.findById(issueKey);
        Map<Integer,String> osMap =testCaseService.findSupportOSAllForMap();
        Map<Integer,String> phaseMap = issueService.findIssuePhaseBeanForMap();
        issueFormBean.setMapOs(osMap);
        issueFormBean.setMapPhase(phaseMap);
        Map testSiteMap= new LinkedHashMap();
        testSiteMap.put(1,"CDL");
        testSiteMap.put(2,"LCFC");
        testSiteMap.put(3,"Compal");
        testSiteMap.put(4,"Wistron");
        testSiteMap.put(5,"Quata");
        issueFormBean.setMapTestsite(testSiteMap);
        String[] param ={issueKey,"Del"};
        commentsList = issueCommentsService.getScrollData(0,10,"issueInstkey=?0 and status!=?1 ",param).getDatas();
        photoList = issuePhotoService.getScrollData(0,10,"issueInstkey =?0 and status!= ?1",param).getDatas();
        return SUCCESS;
    }



}