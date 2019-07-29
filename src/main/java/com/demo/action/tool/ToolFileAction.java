package com.demo.action.tool;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.TestToolBean;
import com.demo.service.common.ToolBeanService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * Created by admin on 2016/9/9.
 */
@Namespace("/phase4")
@ParentPackage("json-default")
public class ToolFileAction extends ActionSupport{
    @Autowired
    private ToolBeanService toolBeanService;
    private String toolKey;
    private TestToolBean toolBean;
    private JSONObject dataMap;
    private File fileInput;
    private String fileInputFileName;
    public TestToolBean getToolBean() {
        return toolBean;
    }

    public void setToolBean(TestToolBean toolBean) {
        this.toolBean = toolBean;
    }

    public void setToolKey(String toolKey) {
        this.toolKey = toolKey;
    }

    public String getToolKey() {
        return toolKey;
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

    @Action(value="UploadToolFile",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String uploadToolFile(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");

       String savePath = "D:\\CTDDataBase\\Attachment\\"+toolKey;
        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        fileInput.renameTo(new File(savePath.toString()+"\\"+ fileInputFileName));
        TestToolBean testToolBean = toolBeanService.findById(toolKey);
        testToolBean.setUploadFileName(fileInputFileName);

        testToolBean.setPath(savePath.toString()+"\\"+ fileInputFileName);
        toolBeanService.update(testToolBean);

        this.dataMap = new JSONObject();
        this.dataMap.put("toolKey",toolKey);
        return SUCCESS;
    }
    @Action(value="UploadMFGToolFile",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String uploadMFGToolFile(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");

        String savePath = "D:\\CTDDataBase\\Attachment\\MFGTool"+toolKey;
        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        fileInput.renameTo(new File(savePath.toString()+"\\"+ fileInputFileName));
        TestToolBean testToolBean = toolBeanService.findById(toolKey);
        testToolBean.setUploadFileName(fileInputFileName);
        testToolBean.setPath(savePath.toString()+"\\"+ fileInputFileName);

        toolBeanService.update(testToolBean);

        this.dataMap = new JSONObject();
        this.dataMap.put("toolKey",toolKey);
        return SUCCESS;
    }
}
