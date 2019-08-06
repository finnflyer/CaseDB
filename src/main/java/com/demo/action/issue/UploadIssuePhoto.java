package com.demo.action.issue;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.model.issue.IssuePhotoBean;
import com.demo.service.issue.IssuePhotoService;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;

/**
 * Created by Admin on 2016/9/10.
 */
@Namespace("/phase4")
@ParentPackage("json-default")
public class UploadIssuePhoto extends ActionSupport {
    @Autowired
    private IssuePhotoService issuePhotoService;
    private String issueKey;
    private JSONObject dataMap;
    private File fileInput;
    private String fileInputFileName;

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public JSONObject getDataMap() {
        return dataMap;
    }

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    public File getFileInput() {
        return fileInput;
    }

    public void setFileInput(File fileInput) {
        this.fileInput = fileInput;
    }

    public String getFileInputFileName() {
        return fileInputFileName;
    }

    public void setFileInputFileName(String fileInputFileName) {
        this.fileInputFileName = fileInputFileName;
    }

    @Action(value="UploadIssuePhoto",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String uploadIssuePhoto(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        String savePath = "D:\\CTDDataBase\\IssueAttachement\\"+issueKey;
        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        fileInput.renameTo(new File(savePath.toString()+"\\"+ fileInputFileName));
        IssuePhotoBean issuePhotoBean = new IssuePhotoBean();
        issuePhotoBean.setStatus("active");
        issuePhotoBean.setIssueInstkey(issueKey);
        issuePhotoBean.setInstkey(Generator.generatorID());
        issuePhotoBean.setUpdateTime(new Date());
        issuePhotoBean.setIssueFileName(fileInputFileName);
        issuePhotoBean.setCreator(user.getUsername());
        issuePhotoBean.setIssuePath(savePath.toString()+"\\"+fileInputFileName);

        issuePhotoService.save(issuePhotoBean);
        this.dataMap = new JSONObject();
        this.dataMap.put("issueKey",issueKey);
        return  SUCCESS;
    }

}
