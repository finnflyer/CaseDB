package com.demo.action.issue;

import com.demo.model.common.User;
import com.demo.model.formbean.IssueFormBean;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssueComments;
import com.demo.model.issue.IssuePhotoBean;
import com.demo.service.issue.IssueCommentsService;
import com.demo.service.issue.IssuePhotoService;
import com.demo.service.issue.IssueService;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.CatoSetting;
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
import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/8.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class IssueAction extends ActionSupport {
    @Autowired
    private IssueService issueService;
    private IssueBean issueBean;
    private String osId;
    private String siteId;
    private String phaseId;
    private IssueFormBean issueFormBean = IssueFormBean.getInstance();
    private String projectInstkey;

    public String getProjectInstkey() {
        return projectInstkey;
    }

    public void setProjectInstkey(String projectInstkey) {
        this.projectInstkey = projectInstkey;
    }

    public IssueFormBean getIssueFormBean() {
        return issueFormBean;
    }

    public void setIssueFormBean(IssueFormBean issueFormBean) {
        this.issueFormBean = issueFormBean;
    }

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public void setIssueBean(IssueBean issueBean) {
        this.issueBean = issueBean;
    }

    public IssueBean getIssueBean() {
        return issueBean;
    }

    @Action(value = "SaveIssueContent", results = {@Result(name = "success",type="redirect",location = "ShowProjectDetail",
            params={"projectKey","%{projectInstkey}"})
    })
    public String SaveIssueContent() {
        phaseId = phaseId.replace(", ", "");
        osId = osId.replace(", ", "");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        projectInstkey = (String) session.getAttribute("projectKey");
        issueBean.setInstkey(Generator.generatorID());
        issueBean.setProjectinstkey(projectInstkey);
        issueBean.setTestSite(Integer.parseInt(siteId));
        issueBean.setPhaseFound(Integer.parseInt(phaseId));
        issueBean.setOsid(Integer.parseInt(osId));
        issueBean.setDescription("N/A");
        issueBean.setIssueStatus("Open");
        issueBean.setOwner(user.getUsername());
        issueService.save(issueBean);

        String savePath = "D:\\CTDDataBase\\IssueAttachement\\"+issueBean.getInstkey();
        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        session.removeAttribute("projectKey");
        return SUCCESS;
    }
    @Autowired
    private IssueCommentsService issueCommentsService;
    @Autowired
    private IssuePhotoService issuePhotoService;
    private List<IssuePhotoBean> photoList;
    private String issueKey;
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

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    @Action(value = "ShowIssueDetail", results = {@Result(name = "success", location = "/jsp/Issue/IssueDetail.jsp")})
    public String showIssueDetail() {
        issueBean = issueService.findById(issueKey);
        issueCatoSetting(issueBean);
        String[] param ={issueKey,"Del"};
        commentsList = issueCommentsService.getScrollData(0,10,"issueInstkey=?0 and status!=?1 ",param).getDatas();
        photoList = issuePhotoService.getScrollData(0,10,"issueInstkey =?0 and status!=?1 ",param).getDatas();
        return SUCCESS;
    }
    public void issueCatoSetting(IssueBean issueBean){
        Map<Integer,String> testSiteMap= new LinkedHashMap();
        testSiteMap.put(1,"CDL");
        testSiteMap.put(2,"LCFC");
        testSiteMap.put(3,"Compal");
        testSiteMap.put(4,"Wistron");
        testSiteMap.put(5,"Quata");
        String testSite = testSiteMap.get(issueBean.getTestSite());
        issueBean.setTestSiteCato(testSite);
        CatoSetting.issueBeanCatoSetting(issueBean);

    }

    @Action(value="DeleteIssue",results={@Result(name="success",type="redirect",location="ShowProjectDetail",
            params={"projectKey","%{issueBean.projectinstkey}"})})
    public String DeleteIssue(){
        issueBean = issueService.findById(issueKey);
        issueBean.setIssueStatus("Del");
        issueService.update(issueBean);
        return SUCCESS;
    }


    @Action(value="UpdateIssue",results={@Result(name="success",type="redirect",location="ShowProjectDetail",
            params={"projectKey","%{issueBean.projectinstkey}"})})
    public String UpdateIssue(){
        phaseId = phaseId.replace(", ", "");
        osId = osId.replace(", ", "");
        IssueBean modifiedBean = issueService.findById(issueKey);
        modifiedBean.setTestSite(Integer.parseInt(siteId));
        modifiedBean.setPhaseFound(Integer.parseInt(phaseId));
        modifiedBean.setOsid(Integer.parseInt(osId));
        modifiedBean.setCaseNum(issueBean.getCaseNum());
        modifiedBean.setComments(issueBean.getComments());
        modifiedBean.setIssueStatus(issueBean.getIssueStatus());
        modifiedBean.setDescription("");
        modifiedBean.setConfiguration(issueBean.getConfiguration());
        modifiedBean.setComponent(issueBean.getComponent());
        modifiedBean.setCreatedate(new Date());
        modifiedBean.setReproduceStep(issueBean.getReproduceStep());
        modifiedBean.setIssueName(issueBean.getIssueName());
        modifiedBean.setEcrNumber(issueBean.getEcrNumber());
        modifiedBean.setPlatform(issueBean.getPlatform());
        modifiedBean.setPriority(issueBean.getPriority());
        modifiedBean.setLanguage(issueBean.getLanguage());


        issueService.update(modifiedBean);
        return SUCCESS;
    }

}
