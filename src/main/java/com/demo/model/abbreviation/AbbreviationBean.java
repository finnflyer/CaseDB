package com.demo.model.abbreviation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by finnf on 2018/8/17.
 */
@Entity
@Table(name = "ctdabbreviations", schema = "", catalog = "casedb")
public class AbbreviationBean implements Serializable {
    private String instkey;
    private String abbreviation;
    private String description;
    private String comments;
    private String uploador;
    private Date uploadTime;
    private String status;

    @Id
    @Column(name = "Instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "Abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "Uploador")
    public String getUploador() {
        return uploador;
    }

    public void setUploador(String uploador) {
        this.uploador = uploador;
    }

    @Basic
    @Column(name = "UploadTime")
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus(){return status;}

    public void setStatus(String status){this.status = status;}

}
