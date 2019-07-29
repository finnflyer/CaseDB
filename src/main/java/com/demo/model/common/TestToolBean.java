package com.demo.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcasetool", schema = "", catalog = "casedb")
public class TestToolBean implements Serializable{
    private String instkey;
    private String toolname;
    private String description;
    private String path;
    private Date uploadtime;
    private String owner;
    private String status;
    private String uploadFileName;
    private String type;

    @Id
    @Column(name = "INSTKEY")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "TOOLNAME")
    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PATH")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "UPLOADTIME")
    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Basic
    @Column(name = "OWNER")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "UploadFileName")
    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    @Basic
    @Column(name="Type")
    public String getType(){return type;}

    public void setType(String type){this.type = type;}
}
