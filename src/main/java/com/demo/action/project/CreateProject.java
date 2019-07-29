package com.demo.action.project;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.common.User;
import com.demo.model.project.ProjectBean;
import com.demo.service.project.ProjectService;
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

/**
 * Created by Admin on 2016/9/7.
 */

@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class CreateProject extends ActionSupport {
    @Autowired
    private ProjectService projectService;
    private String projectName;
    private String projectComments;
    private JSONObject dataMap;


    public JSONObject getDataMap() {
        return dataMap;
    }

    public void setDataMap(JSONObject dataMap) {
        this.dataMap = dataMap;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectComments(String projectComments) {
        this.projectComments = projectComments;
    }

    public String getProjectComments() {
        return projectComments;
    }

    @Action(value="CreateProject",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String CreateProject(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        ProjectBean projectBean = new ProjectBean();
        String projectKey = Generator.generatorID();
        projectBean.setProjectInstkey(projectKey);
        projectBean.setEnable("enable");
        projectBean.setProjectName(projectName);
        projectBean.setComments(projectComments);
        projectBean.setProjectOwner(user.getUsername());
        projectBean.setCreateDate(new Date());
        projectService.save(projectBean);

        this.dataMap = new JSONObject();
        this.dataMap.put("projectKey",projectKey);

        return SUCCESS;
    }

    private String projectInKey;

    public void setProjectInKey(String projectInKey) {
        this.projectInKey = projectInKey;
    }

    public String getProjectInKey() {
        return projectInKey;
    }

    @Action(value="UpdateProject",results={@Result(name="success",type="json",params = {"root", "dataMap"})})
    public String UpdateProject(){

        ProjectBean projectBean = projectService.findById(projectInKey);
        projectBean.setComments(projectComments);
        projectBean.setProjectName(projectName);
        projectService.update(projectBean);
        this.dataMap = new JSONObject();
        this.dataMap.put("projectKey",projectInKey);
        return SUCCESS;
    }
}
