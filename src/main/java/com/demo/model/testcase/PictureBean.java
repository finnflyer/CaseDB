package com.demo.model.testcase;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcasepicture", schema = "", catalog = "casedb")
public class PictureBean implements Serializable{
    private String pictureinstkey;
    private String filepath;
    private String caseinstkey;
    private String filename;
    private String casecontentinstkey;
    private Integer type;
    private Date createdate;

    @Id
    @Column(name = "PICTUREINSTKEY")
    public String getPictureinstkey() {
        return pictureinstkey;
    }

    public void setPictureinstkey(String pictureinstkey) {
        this.pictureinstkey = pictureinstkey;
    }

    @Basic
    @Column(name = "FILEPATH")
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Basic
    @Column(name = "CASEINSTKEY")
    public String getCaseinstkey() {
        return caseinstkey;
    }

    public void setCaseinstkey(String caseinstkey) {
        this.caseinstkey = caseinstkey;
    }

    @Basic
    @Column(name = "FILENAME")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "CASECONTENTINSTKEY")
    public String getCasecontentinstkey() {
        return casecontentinstkey;
    }

    public void setCasecontentinstkey(String casecontentinstkey) {
        this.casecontentinstkey = casecontentinstkey;
    }

    @Basic
    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "CREATEDATE")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }


}
