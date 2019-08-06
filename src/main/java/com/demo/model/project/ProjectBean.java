package com.demo.model.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/9/7.
 */
@Entity
@Table(name = "ctdproject", schema = "", catalog = "casedb")
public class ProjectBean implements Serializable{
    private String projectInstkey;
    private String projectName;
    private Date createDate;
    private String comments;
    private String enable;
    private String projectOwner;

    @Id
    @Column(name = "ProjectInstkey")
    public String getProjectInstkey() {
        return projectInstkey;
    }

    public void setProjectInstkey(String projectInstkey) {
        this.projectInstkey = projectInstkey;
    }

    @Basic
    @Column(name = "ProjectName")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "Enable")
    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "ProjectOwner")
    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }


}
