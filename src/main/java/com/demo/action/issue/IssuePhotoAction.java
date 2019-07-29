package com.demo.action.issue;

import com.demo.model.issue.IssuePhaseBean;
import com.demo.model.issue.IssuePhotoBean;
import com.demo.service.issue.IssuePhotoService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Admin on 2016/9/10.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class IssuePhotoAction extends ActionSupport {
    @Autowired
    private IssuePhotoService issuePhotoService;
    private String issueKey;
    private IssuePhotoBean issuePhotoBean;
    private String issuePhotoKey;


    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public IssuePhotoBean getIssuePhotoBean() {
        return issuePhotoBean;
    }

    public void setIssuePhotoBean(IssuePhotoBean issuePhotoBean) {
        this.issuePhotoBean = issuePhotoBean;
    }

    public String getIssuePhotoKey() {
        return issuePhotoKey;
    }

    public void setIssuePhotoKey(String issuePhotoKey) {
        this.issuePhotoKey = issuePhotoKey;
    }

    @Action(value = "DelIssueFile", results = {@Result(name = "success", type="redirect",location = "ShowIssueDetail",
            params={"issueKey","%{issuePhotoBean.issueInstkey}"})})
    public String DeleteIssuePhotoFile() {
        issuePhotoBean = issuePhotoService.findById(issuePhotoKey);
        issuePhotoBean.setStatus("Del");
        issuePhotoService.update(issuePhotoBean);
        return SUCCESS;
    }
//
    @Action(value = "DownloadIssueFile", results = {@Result(name = "download",
            type = "stream", params = {
            "contentType", "application/octet-stream",
            "inputName", "inputStream", "contentDisposition",
            "attachment;filename=\"${issuePhotoBean.issueFileName}\"", "bufferSize",
            "4096"})})
    public String DownloadFile() {

        return "download";
    }

    /**
     * 获取下载流
     * 对应 annotation 注解里面的 "inputName", "inputStream"
     * 假如 annotation 注解改为 "inputName", "myStream"，则下面的方法则应改为：getMyStream
     *
     * @return InputStream
     */
    public InputStream getInputStream() {
        issuePhotoBean = issuePhotoService.findById(issuePhotoKey);
        String path = issuePhotoBean.getIssuePath();

        try {
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
