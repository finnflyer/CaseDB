package com.demo.model.overtime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by finnf on 2018/6/19.
 */
@Entity
@Table(name = "ctdapplyot", schema = "", catalog = "casedb")
public class ApplyOTBean implements Serializable {
    private String instkey;
    private String userName;
    private Timestamp startTime;
    private Timestamp endTime;
    private Double totalHour;
    private Date inputtime;
    private Boolean active;
    private String type;
    private String Description;
    private Integer employid;

    @Transient
    public Integer getEmployid() {
        return employid;
    }

    public void setEmployid(Integer employid) {
        this.employid = employid;
    }

    @Id
    @Column(name = "Instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "StartTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "EndTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "TotalHour")
    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    @Basic
    @Column(name="type")
    public String getType(){return type;}

    public void setType(String type){ this.type = type;}

    @Basic
    @Column(name = "inputtime")
    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }


    @Basic
    @Column(name = "Active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name="Description")
    public String getDescription(){return Description;}
    public void setDescription(String description){this.Description = description;}
}
