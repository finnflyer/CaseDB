package com.demo.action.tool;

import com.demo.model.common.TestToolBean;
import com.demo.model.common.User;
import com.demo.model.formbean.PageBean;
import com.demo.service.common.ToolBeanService;
import com.demo.util.FileAction;
import com.demo.util.Generator;
import com.demo.util.QueryResult;
import com.demo.util.StringFormat;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;

@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class PressToolHome extends ActionSupport {
    @Autowired
    private ToolBeanService toolBeanService;
    private PageBean pageBean;
    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    @Action(value = "TestToolHome", results = {@Result(name = "success", location = "/jsp/TestTools/TestToolHome.jsp")})
    public String PressToolHome() {

        String where ="status != ?0 and type = ?1";
        String[] param = {"Del","Test"};
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("uploadtime ", "desc");
        QueryResult<TestToolBean> queryResult = toolBeanService.getScrollData(0,50,where,param,orderby);
        for(TestToolBean temp:queryResult.getDatas())
            temp.setDescription(StringFormat.formatStrForHtml(temp.getDescription()));
        pageBean = pageBeanInital(queryResult);
        return SUCCESS;
    }

    @Action(value = "MFGToolHome", results = {@Result(name = "success", location = "/jsp/MFGTools/MFGToolHome.jsp")})
    public String PressMFGToolHome() {
        String where ="status != ?0 and type = ?1";
        String[] param = {"Del","MFG"};
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("uploadtime ", "desc");
        QueryResult<TestToolBean> queryResult = toolBeanService.getScrollData(0,50,where,param,orderby);
        for(TestToolBean temp:queryResult.getDatas())
            temp.setDescription(StringFormat.formatStrForHtml(temp.getDescription()));
        pageBean = pageBeanInital(queryResult);
        return SUCCESS;
    }
    public PageBean pageBeanInital(QueryResult<TestToolBean> resultQurey ){
        int pageNumber = 1;
        int pageSize = 50;
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));
        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;
    }
    private String toolKey;

    public String getToolKey() {
        return toolKey;
    }

    public void setToolKey(String toolKey) {
        this.toolKey = toolKey;
    }

    @Action(value = "Delete", results = {@Result(name = "success", type="redirect",location = "TestToolHome")})
    public String DeleteTool(){
        toolBean = toolBeanService.findById(toolKey);
        toolBean.setStatus("Del");
        toolBeanService.update(toolBean);

        //move the Folder to Delete Folder
        String DeltePath = "D:\\CTDDataBase\\Attachment\\Delete-"+toolKey;
        File fileDir = new File(DeltePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        String SourcePath = toolBean.getPath();
        SourcePath = SourcePath.substring(0,SourcePath.lastIndexOf('\\'));

        FileAction.copyFolder(SourcePath,DeltePath);

        FileAction.deleteDir(new File(SourcePath));
        return SUCCESS;
    }
    private TestToolBean toolBean;

    public TestToolBean getToolBean() {
        return toolBean;
    }

    public void setToolBean(TestToolBean toolBean) {
        this.toolBean = toolBean;
    }

    @Action(value = "ViewTool", results = {@Result(name = "success", location = "/jsp/TestTools/ViewFileDetail.jsp")})
    public String ViewTool(){
        toolBean = toolBeanService.findById(toolKey);
        toolBean.setDescription(StringFormat.formatStrForHtml(toolBean.getDescription()));
        return SUCCESS;
    }
    @Action(value = "ViewMFGTool", results = {@Result(name = "success", location = "/jsp/MFGTools/ViewMFGFile.jsp")})
    public String ViewMFGTool(){
        toolBean = toolBeanService.findById(toolKey);
        toolBean.setDescription(StringFormat.formatStrForHtml(toolBean.getDescription()));
        return SUCCESS;
    }

    @Action(value="DownloadFile",results = {@Result(name="download",
            type = "stream", params = {
            "contentType", "application/octet-stream",
            "inputName", "inputStream", "contentDisposition",
            "attachment;filename=\"${toolBean.uploadFileName}\"", "bufferSize",
            "4096" })})
    public String DownloadFile(){

        return "download";
    }
    /**
     * 获取下载流
     * 对应 annotation 注解里面的 "inputName", "inputStream"
     * 假如 annotation 注解改为 "inputName", "myStream"，则下面的方法则应改为：getMyStream
     * @return InputStream
     */
    public InputStream getInputStream() {
        toolBean = toolBeanService.findById(toolKey);
        String path = toolBean.getPath();

        try {
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value="preSaveFile",results = {@Result(name="success",location="/jsp/TestTools/UploadFileProperty.jsp")})
    public String preSaveFile(){
        return SUCCESS;
    }

    @Action(value="preSaveMFGFile",results= {@Result(name="success",location="/jsp/MFGTools/UploadMFGFileProperty.jsp")})
    public String preSaveMFGFile(){return SUCCESS;}


    public String  toolName;
    public String  description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    @Action(value="SaveToolProperty",results = {@Result(name="success",location = "/jsp/TestTools/UploadFileDetail.jsp")})
    public String saveToolProperty(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("user");
        String instkey = Generator.generatorID();
        toolBean = new TestToolBean();
        toolBean.setInstkey(instkey);
        toolBean.setDescription(description);
        toolBean.setUploadtime(new Date());
        toolBean.setStatus("New");
        toolBean.setType("Test");
        toolBean.setOwner(userInfo.getUsername());
        toolBean.setToolname(toolName);
        toolBeanService.save(toolBean);
        return SUCCESS;
    }
    @Action(value="SaveMFGToolProperty",results = {@Result(name="success",location = "/jsp/MFGTools/UploadMFGFile.jsp")})
    public String saveMFGToolProperty(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("user");
        String instkey = Generator.generatorID();
        toolBean = new TestToolBean();
        toolBean.setInstkey(instkey);
        toolBean.setDescription(description);
        toolBean.setUploadtime(new Date());
        toolBean.setStatus("New");
        toolBean.setType("MFG");
        toolBean.setOwner(userInfo.getUsername());
        toolBean.setToolname(toolName);
        toolBeanService.save(toolBean);
        return SUCCESS;
    }




}
